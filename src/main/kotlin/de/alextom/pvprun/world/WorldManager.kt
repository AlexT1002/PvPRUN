package de.alextom.pvprun.world

import org.bukkit.Bukkit
import org.bukkit.WorldCreator

class WorldManager {
    val world = Bukkit.createWorld(WorldCreator("PvPRUN"))
}