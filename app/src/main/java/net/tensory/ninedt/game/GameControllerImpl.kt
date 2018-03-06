package net.tensory.ninedt.game

import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject

/**
 * Please add a docstring!
 */
class GameControllerImpl(val presenter: GamePresenter) : GameController {

    private val firstPlayerObservable: PublishSubject<FirstPlayer> = PublishSubject.create()
    private val moveObservable: PublishSubject<Int> = PublishSubject.create()
    private val playerOrderDisposable: Disposable

    init {
        playerOrderDisposable = firstPlayerObservable.subscribe({ firstPlayer -> startMatch(firstPlayer) })
    }

    /**
     * Starting point for the game loop.
     *
     * A game begins with requesting the user to choose who goes first.
     * Once that selection is made, play begins.
     */
    override fun startGame() {
        presenter.requestPlayerOrder(firstPlayerObservable)
    }

    override fun setUserGoesFirst(userGoesFirst: Boolean) {
        firstPlayerObservable.onNext(if (userGoesFirst) FirstPlayer.User else FirstPlayer.Computer)
    }

    override fun move(column: Int) {
        moveObservable.onNext(column)
    }

    internal fun startMatch(firstPlayer: FirstPlayer) {
        // Once the match has started, unsubscribe from updates to the player selection.
        playerOrderDisposable.dispose()

        val matchController = MatchController(presenter, firstPlayer == FirstPlayer.User)
        moveObservable.subscribe { lastPlayerMove -> matchController.move(lastPlayerMove) }
        matchController.startMatch()
    }
}