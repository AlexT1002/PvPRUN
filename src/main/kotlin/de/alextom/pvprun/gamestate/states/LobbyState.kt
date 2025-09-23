package de.alextom.pvprun.gamestate.states

import de.alextom.pvprun.PVPRUN
import de.alextom.pvprun.gamestate.statemanager.GameState
import de.alextom.pvprun.gamestate.statemanager.States
import de.alextom.pvprun.listener.PlayerJoinListener
import de.alextom.pvprun.listener.PlayerQuitListener
import de.alextom.pvprun.util.Timer
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.plugin.PluginManager
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable

class LobbyState:  GameState{
    val plugin = JavaPlugin.getPlugin(PVPRUN::class.java)
    val minPlayers: Int = 2
    val timer: Timer = Timer(61, plugin)
    val checkTask: CheckTask = CheckTask()

    override fun start() {
        registerEvents()
        checkTask.runTaskTimer(plugin, 0L, 20L)
        timer.startTimer()
    }

    override fun stop() {
        unregisterEvents()
        checkTask.cancel()
        timer.cancel()
    }

    private fun registerEvents(){
        val pluginManager: PluginManager = Bukkit.getPluginManager()
        pluginManager.registerEvents(PlayerJoinListener(), plugin)
        pluginManager.registerEvents(PlayerQuitListener(), plugin)
    }

    private fun unregisterEvents(){
        PlayerJoinEvent.getHandlerList().unregister(plugin)
        PlayerQuitEvent.getHandlerList().unregister(plugin)
    }

    private fun isEnoughPlayer(): Boolean{
        return Bukkit.getOnlinePlayers().size>=minPlayers
    }

    private fun isServerFull(): Boolean{
        return Bukkit.getOnlinePlayers().size >= Bukkit.getMaxPlayers()
    }

    inner class CheckTask: BukkitRunnable(){
        override fun run() {
            if(isServerFull() or isEnoughPlayer()){
                timer.resumeTimer()
            }else{
                timer.pauseTimer()
                timer.resetTimer()
            }
            if(!timer.isPaused()){
                when(timer.time){
                    60 -> Bukkit.getOnlinePlayers().forEach { player -> player.sendMessage(Component.text("Das Spiel startet in ${timer.time}Sekunden")) }
                    30 -> Bukkit.getOnlinePlayers().forEach { player -> player.sendMessage(Component.text("Das Spiel startet in ${timer.time}Sekunden")) }
                    15 -> Bukkit.getOnlinePlayers().forEach { player -> player.sendMessage(Component.text("Das Spiel startet in ${timer.time}Sekunden")) }
                    10 -> Bukkit.getOnlinePlayers().forEach { player -> player.sendMessage(Component.text("Das Spiel startet in ${timer.time}Sekunden")) }
                    5 -> Bukkit.getOnlinePlayers().forEach { player -> player.sendMessage(Component.text("Das Spiel startet in ${timer.time}Sekunden")) }
                    4 -> Bukkit.getOnlinePlayers().forEach { player -> player.sendMessage(Component.text("Das Spiel startet in ${timer.time}Sekunden")) }
                    3 -> Bukkit.getOnlinePlayers().forEach { player -> player.sendMessage(Component.text("Das Spiel startet in ${timer.time}Sekunden")) }
                    2 -> Bukkit.getOnlinePlayers().forEach { player -> player.sendMessage(Component.text("Das Spiel startet in ${timer.time}Sekunden")) }
                    1 -> Bukkit.getOnlinePlayers().forEach { player -> player.sendMessage(Component.text("Das Spiel startet in ${timer.time}Sekunden")) }
                    0 -> plugin.gameStateManager?.startState(States.INGAME_STATE)
                }
            }
        }

    }
}