package de.alextom.pvprun.scoreboard

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
import org.bukkit.Bukkit
import org.bukkit.scoreboard.Criteria
import org.bukkit.scoreboard.DisplaySlot
import org.bukkit.scoreboard.Objective
import org.bukkit.scoreboard.Scoreboard
import org.bukkit.scoreboard.Team

class LobbyScoreboard() {
    var scoreboard: Scoreboard ?= null
    var objective: Objective ?= null
    var timerTeam: Team?= null
    init {
        val scoreboardManager = Bukkit.getScoreboardManager()
        scoreboard = scoreboardManager.newScoreboard
        objective = scoreboard?.registerNewObjective("PvPRunLobby", Criteria.DUMMY, Component.text("PVPRUN",
            NamedTextColor.DARK_PURPLE))
        if(timerTeam == null){
            timerTeam = scoreboard?.registerNewTeam("timer")
        }
        objective?.displaySlot = DisplaySlot.SIDEBAR
        setScores()
    }

    fun updateScoreboard(){
        scoreboard!!.entries.forEach { scoreboard?.resetScores(it)}
        setScores()
        Bukkit.getOnlinePlayers().forEach {
            player -> player.scoreboard = scoreboard!!
        }
    }

    private fun setScores(){
        objective?.getScore("Timer")?.score = 5
        objective?.getScore(PlainTextComponentSerializer.plainText().serialize(timerTeam?.prefix() ?: Component.text("ERROR")))?.score = 4
        objective?.getScore("")?.score = 3
        objective?.getScore("Online")?.score = 2
        objective?.getScore("${Bukkit.getOnlinePlayers().size}/${Bukkit.getMaxPlayers()}")?.score = 1
    }

    fun setTimer(time: String){
        timerTeam?.prefix(Component.text(time))
    }
}