package net.tensory.ninedt.game

import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject
import net.tensory.ninedt.data.RemotePlayerController

/**
 * Please add a docstring!
 */
internal class GameControllerImpl(val presenter: GamePresenter, private val remotePlayerController: RemotePlayerController) : GameController {

    private val userIsPlayer1Subject: PublishSubject<Boolean> = PublishSubject.create()
    private val moveSubject: PublishSubject<Int> = PublishSubject.create()
    private val playerOrderDisposable: Disposable

    init {
        playerOrderDisposable = userIsPlayer1Subject.subscribe({ firstPlayer -> startMatch(firstPlayer) })
    }

    /**
     * Starting point for the game loop.
     *
     * A game begins with requesting the user to choose who goes first.
     * Once that selection is made, play begins.
     */
    override fun startGame() {
        presenter.requestPlayerOrder(userIsPlayer1Subject)
    }

    override fun setUserGoesFirst(userGoesFirst: Boolean) {
        userIsPlayer1Subject.onNext(userGoesFirst)
    }

    override fun move(column: Int) {
        moveSubject.onNext(column)
    }

    internal fun startMatch(userIsFirstPlayer: Boolean) {
        // Once the match has started, unsubscribe from updates to the player selection.
        playerOrderDisposable.dispose()

        val matchController = MatchController(presenter, remotePlayerController, userIsFirstPlayer)
        moveSubject.subscribe { lastPlayerMove -> matchController.move(lastPlayerMove) }
        matchController.startMatch()
    }
}