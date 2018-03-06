package net.tensory.ninedt.game

/**
 * Please add a docstring!
 */
class DropTokenMatchControllerImpl : DropTokenMatchController {
    private val isUserPlayer1 = true

    override fun move(column: Int, gameMoves: GameState): Boolean {
        if (gameMoves.moveIsValid()) {
            gameMoves.add(column)
            return true
        } else return false
    }

    fun checkDidPlayer1Win()

    fun checkDidPlayer2Win()
}