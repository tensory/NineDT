package net.tensory.ninedt.game

/**
 * Please add a docstring!
 */
interface GameController {
    /**
     * Starting point for the game loop.
     */
    fun startGame()

    /**
     * Set whether the app user is Player 1.
     *
     * @param userGoesFirst True if user will be Player 1.
     */
    fun setUserGoesFirst(userGoesFirst: Boolean)
}