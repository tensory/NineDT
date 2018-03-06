package net.tensory.ninedt.game

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.Subject


/**
 * Controller for a match.
 */
class MatchController(private val gamePresenter: GamePresenter, userIsPlayer1: Boolean) {

    interface RemotePlayerController {
        fun requestNextMove(): Observable<MatchState>
    }

    sealed class Player {
        abstract fun requestNextMove()

        class User(private val gamePresenter: GamePresenter, private val matchStateSubject: Subject<MatchState>) : Player() {
            override fun requestNextMove() {
                gamePresenter.startUserTurn(matchStateSubject)

            }
        }

        class Computer(private val remotePlayerController: RemotePlayerController,
                       private val matchStateObserver: Observer<MatchState>) : Player() {
            override fun requestNextMove() {
                remotePlayerController.requestNextMove().subscribe { gameState ->
                    matchStateObserver.onNext(gameState)
                }
            }

        }
    }

    private val matchState: BehaviorSubject<MatchState> = BehaviorSubject.create()

    private val players: List<Player>
    private var nextTurnPlayerIndex: Int

    init {
        val user = Player.User(gamePresenter, matchState)
        val computer = Player.Computer(RemotePlayerController(), matchState)
        players = if (userIsPlayer1) listOf(user, computer) else listOf(computer, user)
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
            players[nextTurnPlayerIndex].requestNextMove()
        })
    }

    fun startMatch() {
        players[nextTurnPlayerIndex].requestNextMove()
    }

    fun move(column: Int): Boolean {
        return if (matchState.value.isMoveValid(column)) {
            matchState.onNext(matchState.value.add(column))
            true
        } else false
    }
}