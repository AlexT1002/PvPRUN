package de.alextom.pvprun.listener

import de.alextom.pvprun.world.WorldManager
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerMoveEvent

class PlayerMoveListener: Listener {
    @EventHandler
    fun onMove(event: PlayerMoveEvent){
        val player: Player = event.player
        if(player.location.z < 0){
            player.teleport(player.location.add(0.0,0.0,1.0))
        }
        if(player.location.z > WorldManager().border.width){
            player.teleport(player.location.add(0.0,0.0,-1.0))
        }
    }
}