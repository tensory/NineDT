package net.tensory.ninedt.game

import io.reactivex.subjects.BehaviorSubject
import net.tensory.ninedt.data.RemotePlayerController


/**
 * Controller for a match.
 */
class MatchController(private val gamePresenter: GamePresenter, remotePlayerController: RemotePlayerController, userIsFirstPlayer: Boolean) {

    private val matchState: BehaviorSubject<MatchState> = BehaviorSubject.create()

    private val players: List<Player>
    private var nextTurnPlayerIndex: Int

    init {
        val user = Player.User(gamePresenter)
        val computer = Player.Computer(remotePlayerController, matchState)
        players = if (userIsFirstPlayer) listOf(user, computer) else listOf(computer, user)
        nextTurnPlayerIndex = 0

        matchState.subscribe({ matchState ->
            var isWon = false

            // check to see who won
            if (matchState.didPlayer1Win()) {
                if (players[0] is Player.User)
                    gamePresenter.notifyUserWon()
                else
                    gamePresenter.notifyUserLost()
                isWon = true
            } else if (matchState.didPlayer2Win()) {
                if (players[0] is Player.User)
                    gamePresenter.notifyUserLost()
                else
                    gamePresenter.notifyUserWon()
                isWon = true
            }

            if (isWon) return@subscribe

            // if no win, update turn and request next move
            nextTurnPlayerIndex = if (nextTurnPlayerIndex == 1) 0 else 1
            players[nextTurnPlayerIndex].requestNextMove(matchState)
        })
    }

    fun startMatch() {
        players[nextTurnPlayerIndex].requestNextMove(MatchState())
    }

    fun move(column: Int): Boolean {
        return if (matchState.value.isMoveValid(column)) {
            matchState.onNext(matchState.value.add(column))
            true
        } else false
    }
}