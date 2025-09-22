package de.alextom.pvprun.gamestate.states

import de.alextom.pvprun.gamestate.statemanager.GameState

class LobbyState:  GameState{
    override fun start() {
        println("Lobby_STATE started!")
    }

    override fun stop() {
        println("Lobby_STATE stopped!")
    }
}