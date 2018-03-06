package net.tensory.ninedt.game

class MatchState(private val moves: List<Int>) {
    companion object {
        const val NUMBER_OF_ROWS = 4
    }
    private val columns: Array<IntArray> = arrayOf(
            IntArray(NUMBER_OF_ROWS),
            IntArray(NUMBER_OF_ROWS),
            IntArray(NUMBER_OF_ROWS),
            IntArray(NUMBER_OF_ROWS)
    )

    private val rowCounter = intArrayOf(0, 0, 0, 0)

    init {
        moves.forEachIndexed { index, i ->
            val playerNumber = if (index % 2 == 0) 1 else 2
            columns[i][rowCounter[i]] = playerNumber
            rowCounter[i] += 1
        }
    }

    fun didPlayer1Win(): Boolean {
        return checkWin(1)
    }

    fun didPlayer2Win(): Boolean {
        return checkWin(2)
    }

    fun isMoveValid(column: Int): Boolean {
        return columns[column][NUMBER_OF_ROWS - 1] == 0
    }

    /**
     * Returns a new MatchState object, including the new move.
     */
    fun add(column: Int): MatchState {
        return MatchState(moves.plus(column))
    }

    private fun checkWin(playerNumber: Int): Boolean {
        // check for horizontal win
        for (i in 0 until rowCounter.size) {
            if (columns[0][i] == playerNumber &&
                    columns[1][i] == playerNumber &&
                    columns[2][i] == playerNumber &&
                    columns[3][i] == playerNumber) {
                return true
            }
        }

        // check for vertical win
        columns.forEach {
            if (it[0] == playerNumber &&
                    it[1] == playerNumber &&
                    it[2] == playerNumber &&
                    it[3] == playerNumber) {
                return true
            }
        }

        // check diagonal win /
        if (columns[0][0] == playerNumber &&
                columns[1][1] == playerNumber &&
                columns[2][2] == playerNumber &&
                columns[3][3] == playerNumber) {
            return true
        }

        // check diagonal win \
        if (columns[0][3] == playerNumber &&
                columns[1][2] == playerNumber &&
                columns[2][1] == playerNumber &&
                columns[3][0] == playerNumber) {
            return true
        }

        return false
    }
}