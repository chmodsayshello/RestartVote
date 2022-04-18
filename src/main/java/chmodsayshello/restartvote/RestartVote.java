package chmodsayshello.restartvote;

import org.bukkit.plugin.java.JavaPlugin;
import chmodsayshello.restartvote.Commands.*;
import chmodsayshello.restartvote.Listeners.*;

public final class RestartVote extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getCommand("restartvote").setExecutor(new vote());
        getServer().getPluginManager().registerEvents(new Player(), this);
        getLogger().info("RestartVote sucessfully started!");
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        getLogger().info("RestartVote shut down!");
        // Plugin shutdown logic
    }
}
