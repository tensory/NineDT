package net.tensory.ninedt.game

import io.reactivex.Observable

/**
 * Please add a docstring!
 */
interface GamePresenter {
    fun requestPlayerOrder(playerOrderObservable: Observable<Boolean>)

    fun startUserTurn(matchState: MatchState)

    fun notifyUserWon()

    fun notifyUserLost()
}