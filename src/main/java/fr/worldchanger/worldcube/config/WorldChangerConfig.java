package fr.worldchanger.worldcube.config;

import fr.worldchanger.worldcube.Main;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.rmi.server.UID;
import java.util.List;
import java.util.UUID;

public class WorldChangerConfig {
    public File file;
    public YamlConfiguration config;

    public WorldChangerConfig() {
        file = new File(Main.Instance.getDataFolder().getAbsolutePath() + "/WorldChanger.yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        config = YamlConfiguration.loadConfiguration(file);
    }

    public void setWorld(World world) {
        config.set("world", world.getName());
    }
    public void setRadius(int radius) { config.set("radius", radius); }
    public void setTimer(List<String> timer) { config.set("timer", timer); }
    public void setCenter(int[] center) {
        config.set("center.x", center[0]);
        config.set("center.y", center[1]);
    }

    public World getWorld() { return Bukkit.getWorld(config.getString("world")); }
    public int getRadius() { return config.getInt("radius"); }
    public int[] getCenter() { return new int[]{ config.getInt("center.x"), config.getInt("center.y") }; }
    public List<String> getTimer() { return (List<String>)config.getList("timer"); }
    public boolean isEnable() { return config.getBoolean("enable"); }

    public void saveConfig() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
