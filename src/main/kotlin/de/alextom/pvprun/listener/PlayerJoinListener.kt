package de.alextom.pvprun.listener

import de.alextom.pvprun.PVPRUN
import de.alextom.pvprun.world.WorldManager
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.plugin.java.JavaPlugin

class PlayerJoinListener: Listener {
    val plugin = JavaPlugin.getPlugin(PVPRUN::class.java)
    @EventHandler
    fun onJoin(event: PlayerJoinEvent){
        val player: Player = event.player
        val joinMSG: Component = Component.text("[", NamedTextColor.GRAY)
            .append(Component.text("+", NamedTextColor.GREEN))
            .append(Component.text("]", NamedTextColor.GRAY))
            .append(Component.text(" ${player.name}", NamedTextColor.YELLOW))
        event.joinMessage(joinMSG)
        player.teleport(Bukkit.getWorld(WorldManager().fallbackWorld)?.spawnLocation ?: player.location)
        player.scoreboard = plugin.gameStateManager?.lobbyState?.scoreboard?.scoreboard!!
    }
}