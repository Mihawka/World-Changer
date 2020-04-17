package fr.worldchanger.worldcube.commands;

import fr.worldchanger.worldcube.Main;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WorldChangerCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length == 0) {

                return true;
            }
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("world")) {
                    Main.config.setWorld(p.getWorld());
                    Main.config.saveConfig();
                }
                if (args[0].equalsIgnoreCase("center")) {
                    Chunk c = p.getWorld().getChunkAt(p.getLocation());
                    Main.config.setCenter(new int[] { c.getX(), c.getZ() });
                    Main.config.saveConfig();
                }
            }
            if (args.length == 2) {
                if (args[0].equalsIgnoreCase("radius")) {
                    Main.config.setRadius(Integer.parseInt(args[1]));
                    Main.config.saveConfig();
                }
                if (args[0].equalsIgnoreCase("timer")) {
                    Main.config.setTimer(Long.parseLong(args[1]));
                    Main.config.saveConfig();
                }
            }
        }
        return true;
    }
}
