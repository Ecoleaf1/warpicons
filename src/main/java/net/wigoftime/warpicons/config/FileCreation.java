package net.wigoftime.warpicons.config;

import net.wigoftime.warpicons.Main;

import java.io.File;
import java.util.Arrays;

public class FileCreation {
    public FileCreation() {
        File pluginFolder = Main.main.getDataFolder();
        if (!pluginFolder.exists()) pluginFolder.mkdir();

        Main.config = new Config(new File(pluginFolder.getAbsoluteFile()+"/config.yml"));

        File warpFolder = new File(pluginFolder.getAbsolutePath() + "/Warp Categories");
        if (!warpFolder.exists()) {
            warpFolder.mkdir();
        }

        Arrays.stream(warpFolder.listFiles()).forEach(warpConfig -> {
            new WarpConfig(warpConfig);
        });
    }
}
