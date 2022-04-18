package chmodsayshello.restartvote.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import chmodsayshello.restartvote.Commands.vote;

public class Player implements Listener {
    @EventHandler
    public void ondisconnect(PlayerQuitEvent event){
        org.bukkit.entity.Player p = event.getPlayer();
        vote.check_and_remove(p.getName());
        vote.check_and_restart();
    }

    @EventHandler
    public void onchat(PlayerChatEvent event){
        org.bukkit.entity.Player p = event.getPlayer();
        if(event.getMessage().contains("restart") && event.getMessage().contains("server")){
            p.sendMessage(ChatColor.DARK_GREEN + "You think the server needs to restart? Try out /restartvote, if 75 or more % of the players run it, the server will restart...");
        }
    }
}
