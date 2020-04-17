package fr.worldchanger.worldcube;

import fr.worldchanger.worldcube.commands.WorldChangerCommand;
import fr.worldchanger.worldcube.config.WorldChangerConfig;
import fr.worldchanger.worldcube.tools.WorldChangeTask;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.io.File;

public class Main extends JavaPlugin {

    public static JavaPlugin Instance;
    public static WorldChangerConfig config;

    public static World World;
    public static int Radius;
    public static int[] Center;

    public static BukkitTask Task;

    long delay;

    @Override
    public void onEnable() {
        Instance = this;
        configFile();

        delay = config.getTimer();
        World = config.getWorld();
        Radius = config.getRadius();
        Center = config.getCenter();

        getCommand("wc").setExecutor(new WorldChangerCommand());
        if (config.isEnable()) Task = Bukkit.getScheduler().runTaskTimerAsynchronously(this, new WorldChangeTask(), 200, delay);
        System.out.println("World Changer Enable !");
    }

    public void configFile() {
        File f = new File(getDataFolder().getAbsolutePath());
        if (!f.exists()) f.mkdir();
        config = new WorldChangerConfig();
    }

}
