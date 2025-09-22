package de.alextom.pvprun.gamestate.states

import de.alextom.pvprun.gamestate.statemanager.GameState

class EndState: GameState {
    override fun start() {
        println("End_STATE started!")
    }

    override fun stop() {
        println("End_STATE stopped!")
    }
}