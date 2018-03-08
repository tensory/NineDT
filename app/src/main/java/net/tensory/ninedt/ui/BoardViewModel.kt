package net.tensory.ninedt.ui

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import net.tensory.ninedt.game.MatchState

/**
 * View model for a board.
 */
class BoardViewModel : ViewModel() {
    private var matchState: MutableLiveData<MatchState> = MutableLiveData()

    fun update(matchState: MatchState) {
        this.matchState.value = matchState
    }
}