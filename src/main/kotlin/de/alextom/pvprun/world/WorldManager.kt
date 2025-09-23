package de.alextom.pvprun.world

import de.alextom.pvprun.world.border.Border
import org.bukkit.Bukkit
import org.bukkit.WorldCreator
import java.io.File

class WorldManager {
    val pvprunWorld: String = "PVPRUN"
    val fallbackWorld: String = "world"
    val border: Border = Border(20)

    fun createWorld(){
        Bukkit.createWorld(WorldCreator(pvprunWorld))
    }

    fun deleteWorld(){
        Bukkit.unloadWorld("PvPRUN", false)
        val worldFolder: File = File(Bukkit.getWorldContainer(), pvprunWorld)
        if(worldFolder.exists()){
            worldFolder.listFiles().forEach { file -> file.delete() }
            worldFolder.delete()
        }
    }
}