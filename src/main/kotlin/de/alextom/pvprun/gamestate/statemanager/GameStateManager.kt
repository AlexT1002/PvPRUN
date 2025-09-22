package de.alextom.pvprun.gamestate.statemanager

import de.alextom.pvprun.gamestate.states.LobbyState

class GameStateManager {
    val lobbyState: GameState = LobbyState()
    val inGameState: GameState = LobbyState()
    val endState: GameState = LobbyState()

    var actState: GameState? = null

    fun startState(state: States){
        if(!isRunning(state)) return
        actState?.stop()
        actState = getState(state)
        actState?.start()
    }

    private fun isRunning(state: States): Boolean{
        return actState == getState(state)
    }

    private fun getState(state: States): GameState{
        return when(state){
            States.LOBBY_STATE -> lobbyState
            States.INGAME_STATE -> inGameState
            States.END_STATE -> endState
        }
    }
}