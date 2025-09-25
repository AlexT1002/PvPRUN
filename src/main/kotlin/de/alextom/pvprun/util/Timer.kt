package de.alextom.pvprun.util

import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable

class Timer(var time: Int, val plugin: JavaPlugin) {
    private val safeTime: Int = time
    private val timer: TimerTask = TimerTask()
    private var paused: Boolean = false

    fun startTimer(){
        timer.runTaskTimer(plugin, 0L, 20L)
    }

    fun resumeTimer(){
        if(paused) paused = false
    }

    fun pauseTimer(){
        if(!paused) paused = true
    }

    fun resetTimer(){
        time = safeTime
    }

    fun isPaused(): Boolean {
        return paused
    }

    fun cancel(){
        timer.cancel()
    }

    inner class TimerTask: BukkitRunnable(){
        override fun run() {
            if(paused) return
            time--

        }
    }
}