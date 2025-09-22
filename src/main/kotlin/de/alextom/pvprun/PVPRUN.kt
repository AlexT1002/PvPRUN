package de.alextom.pvprun

import de.alextom.pvprun.gamestate.statemanager.GameStateManager
import de.alextom.pvprun.gamestate.statemanager.States
import org.bukkit.plugin.java.JavaPlugin

class PVPRUN : JavaPlugin() {

    override fun onEnable() {
        val gameStateManager = GameStateManager()
        gameStateManager.startState(States.LOBBY_STATE)
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}
