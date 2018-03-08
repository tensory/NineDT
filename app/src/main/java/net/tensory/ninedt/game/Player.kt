package net.tensory.ninedt.game

import io.reactivex.Observer
import io.reactivex.subjects.Subject
import net.tensory.ninedt.data.RemotePlayerController

sealed class Player {
    abstract fun requestNextMove(currentState: MatchState)

    class User(private val gamePresenter: GamePresenter, private val matchStateSubject: Subject<MatchState>) : Player() {
        override fun requestNextMove(currentState: MatchState) {
            gamePresenter.startUserTurn(matchStateSubject)

        }
    }

    class Computer(private val remotePlayerController: RemotePlayerController,
                   private val matchStateObserver: Observer<MatchState>) : Player() {
        override fun requestNextMove(currentState: MatchState) {
            remotePlayerController.requestNextMove(currentState.moves).subscribe { gameState ->
                matchStateObserver.onNext(gameState)
            }
        }
    }
}