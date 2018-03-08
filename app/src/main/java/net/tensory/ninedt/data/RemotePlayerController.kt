package net.tensory.ninedt.data

import io.reactivex.Observable
import net.tensory.ninedt.game.MatchState

interface RemotePlayerController {
    fun requestNextMove(moves: IntArray): Observable<MatchState>
}