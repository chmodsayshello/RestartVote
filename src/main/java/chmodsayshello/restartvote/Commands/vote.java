package chmodsayshello.restartvote.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class vote implements CommandExecutor {

    static List voters = new ArrayList();

    public boolean onCommand(CommandSender sender, Command command, String label, String[]args) {
        if(!(sender instanceof Player)){
            sender.sendMessage(ChatColor.RED +"Sorry, but you have to be a player in order to vote on restarts!");
        }else{
            boolean already_voted = false;
            try{
                for(int i=0; i<voters.size(); i++){
                    if(voters.get(i).equals(sender.getName())){
                        already_voted = true;
                    }
                }

                if(already_voted){
                    sender.sendMessage(ChatColor.RED+ "Sorry, but you can only vote once");
                }
                else {
                    voters.add(sender.getName());

                    for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                        p.sendMessage(ChatColor.YELLOW + sender.getName() + " has voted for the restart! " + Integer.toString(voters.size()*100 / Bukkit.getOnlinePlayers().size()) + "% of at least 75% have voted!");
                    }
                    check_and_restart();
                }

            }catch(Exception e){
                sender.sendMessage(ChatColor.RED + "Something went wrong :(");
                sender.sendMessage(e.toString());
            }
        }
        return true;
    }

    public static void check_and_remove(String Name){
        for(int i = 0; i<voters.size(); i++){
            if(voters.get(i).equals(Name)){
                voters.remove(i);
            }
        }
    }

    public static void check_and_restart(){
        if (voters.size() / Bukkit.getServer().getOnlinePlayers().size() >= 0.75) {
            String file_path = Bukkit.getServer().getWorldContainer().getAbsolutePath().substring(0, Bukkit.getServer().getWorldContainer().getAbsolutePath().length() -1)+ "start";
            File bat_script = new File(file_path + ".bat");
            File sh_script = new File(file_path + ".sh");
            if(!bat_script.exists() && !sh_script.exists()){
                Bukkit.getLogger().warning("start.sh (start.bat if the server is hosted on a windows machine) is missing! your server might not be able to restart...");
            }
            for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                p.kickPlayer("Server is restarting!");
            }
            Bukkit.getServer().spigot().restart();
        }
    }
}
