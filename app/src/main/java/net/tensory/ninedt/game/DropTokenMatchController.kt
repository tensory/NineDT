package net.tensory.ninedt.game

/**
 * Controller for a Drop Token match.
 */
interface DropTokenMatchController {
    /**
     * @param column The column in which the player will place a token.
     *
     * @param moves The current board.
     *
     * @return true if the move is possible on the current board,
     *         false if not possible.
     */
    fun move(column: Int, gameMoves: GameState): Boolean
}