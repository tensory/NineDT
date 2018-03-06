package net.tensory.ninedt.game

import io.reactivex.subjects.PublishSubject

/**
 * Please add a docstring!
 */
class GameControllerImpl(val presenter: GamePresenter) : GameController {
    private var userIsPlayer1 = false

    private val firstPlayer: PublishSubject<FirstPlayer> = PublishSubject.create()

    init {
        firstPlayer.subscribe({ firstPlayer -> startMatch(firstPlayer) })
    }

    /**
     * Starting point for the game loop.
     *
     * A game begins with requesting the user to choose who goes first.
     * Once that selection is made, play begins.
     */
    override fun startGame() {
        presenter.requestPlayerOrder(firstPlayer)
    }

    override fun setUserGoesFirst(userGoesFirst: Boolean) {
        firstPlayer.onNext(if (userGoesFirst) FirstPlayer.User else FirstPlayer.Computer)
    }

    internal fun startMatch(firstPlayer: FirstPlayer) {
        
    }
}