package net.tensory.ninedt.ui

import io.reactivex.Observable

/**
 * View contract for Board.
 */
internal interface MoveDelegate {
    fun getMove(): Observable<Int>
}