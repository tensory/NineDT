package net.tensory.ninedt

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjection
import io.reactivex.disposables.Disposable
import net.tensory.ninedt.data.RemotePlayerController
import net.tensory.ninedt.game.*
import net.tensory.ninedt.ui.BoardFragment
import net.tensory.ninedt.ui.MoveDelegate
import net.tensory.ninedt.ui.PlayerOrderFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity(), GameView, PlayerOrderFragment.OnChooseFirstPlayerListener {
    override fun startUserTurn(matchState: MatchState) {
        boardFragment.viewModel.update(matchState)

    }

    override fun onChooseFirstPlayer(userGoesFirst: Boolean) {
        gameController.setUserGoesFirst(userGoesFirst)
        val boardFragment = BoardFragment()
        userMoveDisposable = (boardFragment as MoveDelegate).getMove()
                .subscribe { column ->
                    gameController.move(column)
                }
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, boardFragment)
                .commit()
    }

    @Inject
    lateinit var remotePlayerController: RemotePlayerController

    @Inject
    lateinit var gamePresenter: GamePresenter

    lateinit var userMoveDisposable: Disposable

    lateinit var gameController: GameController

    val boardFragment = BoardFragment()

    override fun presentOrderChooser() {
        supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, PlayerOrderFragment())
                .commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gameController = GameControllerImpl(gamePresenter, remotePlayerController)
        gameController.startGame()
    }

    override fun onDestroy() {
        super.onDestroy()
        userMoveDisposable.dispose()
    }
}
