package fr.worldchanger.worldcube.commands;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.block.Block;
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
                Chunk c = p.getWorld().getChunkAt(p.getLocation());
                for (int i = 0; i < 16; i++) {
                    for (int j = 0; j < 255; j++) {
                        for (int k = 0; k < 16; k++) {
                            Block b = c.getBlock(i, j ,k);
                            if (b.getType() == Material.SAND) {
                                b.setType(Material.GRASS);
                            }
                        }
                    }
                }
                return true;
            }
        }
        return true;
    }
}
