package de.alextom.pvprun.listener

import net.kyori.adventure.text.Component
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerQuitEvent

class PlayerQuitListener: Listener {
    @EventHandler
    fun onQuit(event: PlayerQuitEvent){
        val player: Player = event.player
        event.quitMessage(Component.text("§f[§c-§f]§6 ${player.name}"))
    }
}