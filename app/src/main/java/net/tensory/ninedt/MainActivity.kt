package net.tensory.ninedt

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import net.tensory.ninedt.game.GameControllerImpl

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gameController = GameControllerImpl()
        gameController.startGame()

        // what does a presenter do? it requests user action
        // "start game" means that a match will be started
        // the game controller will tell the game presenter to show the chooser view
        // and set up the subscription for the match.

        // game controller tells game presenter to present the request game state
        // there should be an observable in the game presenter that the controller subscribes to to
        // see when to start the match
    }
}
