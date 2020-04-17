package fr.worldchanger.worldcube.tools;

import fr.worldchanger.worldcube.Main;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

public class WorldChangeTask implements Runnable {
    @Override
    public void run() {
        System.out.println("WORLD CHANGER APPLIED");
        World w = Main.World;
        int[] center = new int[2];
        center[0] = Main.Center[0] - Main.Radius;
        center[1] = Main.Center[1] - Main.Radius;

        int count = Main.Radius * 2 +1;

        //X++
        for (int i = 0; i < count; i++) {
            if (i != 0) ++center[0];
            loopChunk(center, w);
        }
        //Y++
        for (int i = 0; i < count; i++) {
            if (i != 0) ++center[1];
            loopChunk(center, w);
        }
        //X--
        for (int i = 0; i < count; i++) {
            if (i != 0) --center[0];
            loopChunk(center, w);
        }
        //Y--
        for (int i = 0; i < count; i++) {
            if (i != 0) --center[1];
            loopChunk(center, w);
        }

        if (--Main.Radius < 0) {
            Main.Task.cancel();
            System.out.println("WORLD CHANGER END");
        }
    }

    void loopChunk(int[] center, World w) {
        Chunk c = w.getChunkAt(center[0], center[1]);
        for (int x = 0; x < 16; x++)
            for (int y = 0; y < 256; y++)
                for (int z = 0; z < 16; z++) {
                    Block b = c.getBlock(x, y ,z);
                    if (b.getType() == Material.SAND) Main.Instance.getServer().getScheduler().runTask(Main.Instance, () -> b.setType(Material.GRASS));
                }
    }
}
