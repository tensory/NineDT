package net.tensory.ninedt.game

interface GameView {
    fun presentOrderChooser()

    fun startUserTurn()

    fun endUserTurn()
}