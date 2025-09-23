package de.alextom.pvprun

import de.alextom.pvprun.commands.StateCommand
import de.alextom.pvprun.gamestate.statemanager.GameStateManager
import de.alextom.pvprun.gamestate.statemanager.States
import de.alextom.pvprun.world.WorldManager
import org.bukkit.plugin.java.JavaPlugin

class PVPRUN : JavaPlugin() {
    var gameStateManager: GameStateManager ?= null
    val worldManager: WorldManager = WorldManager()

    override fun onEnable() {
        worldManager.createWorld()
        gameStateManager = GameStateManager()
        gameStateManager?.startState(States.LOBBY_STATE)
        getCommand("state")?.setExecutor(StateCommand())
    }

    override fun onDisable() {
        worldManager.deleteWorld()
    }
}
