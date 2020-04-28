package fr.worldchanger.worldcube;

import fr.worldchanger.worldcube.commands.WorldChangerCommand;
import fr.worldchanger.worldcube.config.WorldChangerConfig;
import fr.worldchanger.worldcube.tools.WorldChangeTask;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main extends JavaPlugin {

    public static JavaPlugin Instance;
    public static WorldChangerConfig config;

    public static World World;
    public static int Radius;
    public static int[] Center;
    public static List<String> Delay;

    public static List<BukkitTask> Tasks;
    public static boolean IsEnable;

    @Override
    public void onEnable() {
        Instance = this;
        configFile();
        loadConfigWC();

        IsEnable = config.isEnable();
        getCommand("wc").setExecutor(new WorldChangerCommand());
        if (IsEnable) { CreateTask(); }

        System.out.println("World Changer Enable !");
    }

    @Override
    public void onDisable() {
        saveConfigWC();
        System.out.println("World Changer Disable !");
    }

    public void configFile() {
        File f = new File(getDataFolder().getAbsolutePath());
        if (!f.exists()) f.mkdir();
        config = new WorldChangerConfig();
    }

    public static void loadConfigWC() {
        Tasks = new ArrayList<BukkitTask>();
        Delay = config.getTimer();
        World = config.getWorld();
        Radius = config.getRadius();
        Center = config.getCenter();
    }

    public static void saveConfigWC() {
        config.setTimer(Delay);
        config.setWorld(World);
        config.setRadius(Radius);
        config.setCenter(Center);
        config.saveConfig();
    }

    public static void CreateTask() {
        for (String delay : Delay) {
            long time = 1728000;
            Tasks.add(
                    Bukkit.getScheduler().runTaskTimerAsynchronously(Main.Instance, new WorldChangeTask(), GetTimer(delay), time)
            );
        }
    }

    public static void StopTask() {
        int length = Main.Tasks.size();
        for (int i = 0; i < length; i++) {
            Main.Tasks.get(i).cancel();
        }
        Main.Tasks.clear();
    }

    public static long GetTimer(String delay) {
        Date d = new Date(),
                now = new Date();

        String[] time = delay.split(":");
        d.setHours(Integer.parseInt(time[0]));
        d.setMinutes(Integer.parseInt(time[1]));
        d.setSeconds(0);

        if (now.getTime() > d.getTime()) {
            now = new Date(now.getYear(), now.getMonth(), now.getDay()+1);
            return ((now.getTime() - d.getTime()) / 1000) * 20;
        } else {
            return ((d.getTime() - now.getTime()) / 1000) * 20;
        }
    }
}
