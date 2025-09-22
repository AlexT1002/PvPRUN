package de.alextom.pvprun.gamestate.states

import de.alextom.pvprun.gamestate.statemanager.GameState
import org.bukkit.Bukkit
import org.bukkit.World
import java.io.File

class IngameState: GameState {
    override fun start() {
        val world: World? = Bukkit.getWorld("PvPRUN")
        for(player in Bukkit.getOnlinePlayers()){
            player.teleport(world?.spawnLocation ?: player.location)
        }
    }

    override fun stop() {
        Bukkit.getOnlinePlayers().forEach { player -> player.teleport(Bukkit.getWorld("world")?.spawnLocation ?: Bukkit.getWorlds().first().spawnLocation)}
        Bukkit.unloadWorld("PvPRUN", false)
        val worldFolder: File = File(Bukkit.getWorldContainer(), "PvPRUN")
        worldFolder.listFiles().forEach { file -> file.delete()}
        worldFolder.delete()
    }
}