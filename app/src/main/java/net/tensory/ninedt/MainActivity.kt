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
    }

    // What needs to happen for layout?
    // Layout must be created
    // View should be updated to show one color or the other
    // View should prompt user who should go first

}
