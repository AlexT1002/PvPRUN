package de.alextom.pvprun.listener

import de.alextom.pvprun.world.WorldManager
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class PlayerJoinListener: Listener {
    @EventHandler
    fun onJoin(event: PlayerJoinEvent){
        val player: Player = event.player
        event.joinMessage(Component.text("§f[§2+§f]§6 ${player.name}"))
        player.teleport(Bukkit.getWorld(WorldManager().fallbackWorld)?.spawnLocation ?: player.location)
    }
}