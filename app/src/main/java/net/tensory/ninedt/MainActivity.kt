package net.tensory.ninedt

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjection
import net.tensory.ninedt.data.RemotePlayerController
import net.tensory.ninedt.game.GameController
import net.tensory.ninedt.game.GameControllerImpl
import net.tensory.ninedt.game.GamePresenter
import net.tensory.ninedt.game.GameView
import net.tensory.ninedt.ui.PlayerOrderFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity(), GameView, PlayerOrderFragment.OnChooseFirstPlayerListener {
    override fun onChooseFirstPlayer(userGoesFirst: Boolean) {
        gameController.setUserGoesFirst(userGoesFirst)
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, BoardFragment())
                .commit()
    }

    @Inject
    lateinit var remotePlayerController: RemotePlayerController

    @Inject
    lateinit var gamePresenter: GamePresenter

    lateinit var gameController: GameController

    override fun startUserTurn() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun endUserTurn() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

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
}
