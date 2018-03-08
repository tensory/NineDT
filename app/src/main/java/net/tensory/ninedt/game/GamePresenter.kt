package net.tensory.ninedt.game

import io.reactivex.Observable
import io.reactivex.Observer

/**
 * Please add a docstring!
 */
interface GamePresenter {
    fun requestPlayerOrder(playerOrderObservable: Observable<Boolean>)

    fun startUserTurn(matchStateObserver: Observer<MatchState>)

    fun endUserTurn()

    fun notifyUserWon()

    fun notifyUserLost()

    /**
     * how does this work?
     * game view receives an updated game state - when it receives one, update the view
     * game presenter tells the game view to enable clicks on the game surface
     * when a click is received (for a move),
     */
}