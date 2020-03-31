package fr.worldchanger.worldcube;

import fr.worldchanger.worldcube.commands.WorldChangerCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("wc").setExecutor(new WorldChangerCommand());

        System.out.println("World Changer Enable !");
    }

}
