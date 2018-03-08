package net.tensory.ninedt.game

import android.util.Log
import io.reactivex.Observer
import net.tensory.ninedt.data.RemotePlayerController

sealed class Player {
    abstract fun requestNextMove(currentState: MatchState)

    class User(private val gamePresenter: GamePresenter) : Player() {
        override fun requestNextMove(currentState: MatchState) {
            gamePresenter.startUserTurn(currentState)
        }
    }

    class Computer(private val remotePlayerController: RemotePlayerController,
                   private val matchStateObserver: Observer<MatchState>) : Player() {
        override fun requestNextMove(currentState: MatchState) {
            remotePlayerController.requestNextMove(currentState.moves).subscribe { gameState ->
                Log.d("Computer", gameState.moves.toString())
                matchStateObserver.onNext(gameState)
            }
        }
    }
}