package fr.worldchanger.worldcube.commands;

import fr.worldchanger.worldcube.Main;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class WorldChangerCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("start")) {
                    if (!Main.IsEnable) {
                        Main.IsEnable = true;
                        Main.CreateTask();
                        p.sendMessage("Démarrage forcé!");
                    }
                    else {
                        p.sendMessage("Task déjà en cours!");
                    }
                }
                if (args[0].equalsIgnoreCase("stop")) {
                    if (Main.IsEnable) {
                        Main.IsEnable = false;
                        Main.StopTask();
                        p.sendMessage("Stop forcé!");
                    }
                    else {
                        p.sendMessage("Task déjà arrêté!");
                    }
                }
                if (args[0].equalsIgnoreCase("world")) {
                    Main.config.setWorld(p.getWorld());
                    Main.config.saveConfig();
                    p.sendMessage("Monde définit!");
                }
                if (args[0].equalsIgnoreCase("center")) {
                    Chunk c = p.getWorld().getChunkAt(p.getLocation());
                    Main.config.setCenter(new int[] { c.getX(), c.getZ() });
                    Main.config.saveConfig();
                    p.sendMessage("Centre du monde définit!");
                }
                if (args[0].equalsIgnoreCase("relaod")) {
                    Main.loadConfigWC();
                    p.sendMessage("Configuration rechargé!");
                }
            }
            if (args.length == 2) {
                if (args[0].equalsIgnoreCase("radius")) {
                    Main.config.setRadius(Integer.parseInt(args[1]));
                    Main.config.saveConfig();
                    p.sendMessage("Radius définit à " + args[1]);
                }
            }
        }
        return true;
    }
}
