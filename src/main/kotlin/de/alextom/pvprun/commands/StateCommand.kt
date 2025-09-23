package de.alextom.pvprun.commands

import de.alextom.pvprun.PVPRUN
import de.alextom.pvprun.gamestate.statemanager.States
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.plugin.java.JavaPlugin

class StateCommand: CommandExecutor {
    val plugin = JavaPlugin.getPlugin(PVPRUN::class.java)
    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): Boolean {
        if(args.size == 1){
            when(args[0]){
                "0" -> plugin.gameStateManager?.startState(States.LOBBY_STATE)
                "1" -> plugin.gameStateManager?.startState(States.INGAME_STATE)
                "2" -> plugin.gameStateManager?.startState(States.END_STATE)
            }
        }
        return false;
    }
}