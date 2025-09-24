package de.alextom.pvprun.listener

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerQuitEvent

class PlayerQuitListener: Listener {
    @EventHandler
    fun onQuit(event: PlayerQuitEvent){
        val player: Player = event.player
        val quitMSG: Component = Component.text("[", NamedTextColor.GRAY)
            .append(Component.text("-", NamedTextColor.RED))
            .append(Component.text("]", NamedTextColor.GRAY))
            .append(Component.text(" ${player.name}", NamedTextColor.YELLOW))
        event.quitMessage(quitMSG)
    }
}