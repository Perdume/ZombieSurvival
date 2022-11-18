package Command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import zombiesurvival.zs.Main;

public class user implements CommandExecutor {
    private final String PREFIX = ChatColor.GRAY + "[" + ChatColor.GOLD + "ZombieSurvival" + ChatColor.GRAY + "]";
    private Main bs = Main.getPlugin(Main.class);
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player pl = (Player) sender;
        if (bs.gamemanager.PlayerIsPlaying(pl)){
            return true;
        }
        if(args.length == 0) {
            sender.sendMessage(PREFIX + ChatColor.GOLD + "Bowshot by Perdume");
            return true;
        }
        if(args[0].equalsIgnoreCase("help")) {
            return true;
        }
        if(args[0].equalsIgnoreCase("join")) {
            bs.match.join((Player) sender);
            Bukkit.broadcastMessage(ChatColor.GREEN + "Bowshot: " + bs.match.getMatchingPlayers().size());
            if (bs.match.getMatchingPlayers().size() == 2){
                AutoStart();
            }
            return true;
        }
        if(args[0].equalsIgnoreCase("leave")) {
            bs.match.leave((Player) sender);
            return true;
        }
        if(args[0].equalsIgnoreCase("spect")) {
            bs.js.openInventory((HumanEntity) sender);
            return true;
        }
        return true;
    }
}
