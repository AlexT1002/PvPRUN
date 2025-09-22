package de.alextom.pvprun

import de.alextom.pvprun.commands.StateCommand
import de.alextom.pvprun.gamestate.statemanager.GameStateManager
import de.alextom.pvprun.gamestate.statemanager.States
import de.alextom.pvprun.world.WorldManager
import org.bukkit.plugin.java.JavaPlugin

class PVPRUN : JavaPlugin() {
    val gameStateManager: GameStateManager = GameStateManager()

    override fun onEnable() {
        WorldManager()
        gameStateManager.startState(States.LOBBY_STATE)
        getCommand("state")?.setExecutor(StateCommand())
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}
