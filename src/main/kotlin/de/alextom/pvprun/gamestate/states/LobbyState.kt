package de.alextom.pvprun.gamestate.states

import de.alextom.pvprun.PVPRUN
import de.alextom.pvprun.gamestate.statemanager.GameState
import de.alextom.pvprun.gamestate.statemanager.States
import de.alextom.pvprun.listener.PlayerJoinListener
import de.alextom.pvprun.listener.PlayerQuitListener
import de.alextom.pvprun.util.Timer
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.plugin.PluginManager
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable
import org.bukkit.scoreboard.Criteria
import org.bukkit.scoreboard.DisplaySlot
import org.bukkit.scoreboard.Objective
import org.bukkit.scoreboard.Scoreboard
import org.bukkit.scoreboard.ScoreboardManager

class LobbyState:  GameState{
    val plugin = JavaPlugin.getPlugin(PVPRUN::class.java)
    val minPlayers: Int = 2
    val timer: Timer = Timer(61, plugin)
    val checkTask: CheckTask = CheckTask()
    val sbManager: ScoreboardManager = Bukkit.getScoreboardManager()
    val sb : Scoreboard = sbManager.newScoreboard
    var sbObjective: Objective = sb.registerNewObjective("LobbySB", Criteria.DUMMY, Component.text("PVPRUN", NamedTextColor.DARK_PURPLE))

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

    fun updateScoreboard(){
        sb.entries.forEach { sb.resetScores(it) }
        sbObjective.displaySlot = DisplaySlot.SIDEBAR
        sbObjective.getScore("Timer").score = 4
        sbObjective.getScore(if(timer.isPaused()) "Not Enough Player" else "${timer.time}").score = 3
        sbObjective.getScore("").score = 2
        sbObjective.getScore("Online").score = 1
        sbObjective.getScore("${Bukkit.getOnlinePlayers().size}/${Bukkit.getMaxPlayers()}").score = 0
    }

    inner class CheckTask: BukkitRunnable() {
        override fun run() {
            if(isServerFull() || isEnoughPlayer()){
                timer.resumeTimer()
            } else {
                timer.pauseTimer()
                timer.resetTimer()
            }

            if(!timer.isPaused()){
                when(timer.time){
                    60, 30, 15, 10, 5, 4, 3, 2, 1 -> Bukkit.getOnlinePlayers().forEach { player ->
                        player.sendMessage(Component.text("Das Spiel startet in ${timer.time} Sekunden"))
                    }
                    0 -> plugin.gameStateManager?.startState(States.INGAME_STATE)
                }
            }
            Bukkit.getOnlinePlayers().forEach { player -> player.scoreboard = sb }
            updateScoreboard()
        }
    }
}