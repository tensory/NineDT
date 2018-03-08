package net.tensory.ninedt.game

import io.reactivex.Observable
import io.reactivex.Observer

/**
 * Presenter of game views.
 */
internal class GamePresenterImpl(private val gameView: GameView) : GamePresenter {
    override fun requestPlayerOrder(playerOrderObservable: Observable<Boolean>) {
        gameView.presentOrderChooser()
    }

    override fun startUserTurn(matchStateObserver: Observer<MatchState>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun endUserTurn() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun notifyUserWon() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun notifyUserLost() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}