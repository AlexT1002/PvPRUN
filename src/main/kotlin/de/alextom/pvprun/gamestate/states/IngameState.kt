package de.alextom.pvprun.gamestate.states

import de.alextom.pvprun.PVPRUN
import de.alextom.pvprun.gamestate.statemanager.GameState
import de.alextom.pvprun.listener.PlayerMoveListener
import de.alextom.pvprun.world.WorldManager
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.World
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.plugin.PluginManager
import org.bukkit.plugin.java.JavaPlugin

class IngameState: GameState {
    val plugin = JavaPlugin.getPlugin(PVPRUN::class.java)
    val pvprunWorld: World? = Bukkit.getWorld(WorldManager().pvprunWorld)
    val fallbackWorld: World? = Bukkit.getWorld(WorldManager().fallbackWorld)

    override fun start() {
        pvprunWorld?.spawnLocation = Location(pvprunWorld
            ,0.0
            ,pvprunWorld.getHighestBlockAt(0, WorldManager().border.width/2).y.toDouble()
            ,WorldManager().border.width.toDouble()/2)

        Bukkit.getOnlinePlayers().forEach { player -> player.teleport(pvprunWorld?.spawnLocation ?: player.location) }
        registerEvents()
    }

    override fun stop() {
        Bukkit.getOnlinePlayers().forEach { player -> player.teleport(fallbackWorld?.spawnLocation ?: Bukkit.getWorlds().first().spawnLocation)}
        unregisterEvents()
    }


    private fun registerEvents(){
        val pluginManager: PluginManager = Bukkit.getPluginManager()
        pluginManager.registerEvents(PlayerMoveListener(), plugin)
    }

    private fun unregisterEvents(){
        PlayerMoveEvent.getHandlerList().unregister(plugin)
    }
}