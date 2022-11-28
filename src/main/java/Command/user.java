package Command;

import Team.Team;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import zombiesurvival.zs.Main;

import java.util.ArrayList;

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
            sender.sendMessage(PREFIX + ChatColor.GOLD + "Zs by Perdume");
            return true;
        }
        if(args[0].equalsIgnoreCase("help")) {
            return true;
        }
        if(args[0].equalsIgnoreCase("create")){
            Team t = bs.teammanager.GetTeamMem(pl);
            if (t == null) {
                bs.teammanager.CreateTeam(pl);
                pl.sendMessage("Created");
            }
        }
        if(args[0].equalsIgnoreCase("join")) {
            Team t = bs.teammanager.GetTeam(Bukkit.getPlayer(args[2]));
            if (t != null){
                t.join(pl);
                pl.sendMessage("JOINED");
            }
            else{
                pl.sendMessage("ERROR!");
            }
        }
        if(args[0].equalsIgnoreCase("leave")) {
            Team t = bs.teammanager.GetTeamMem(pl);
            if (t.getLeader() == pl){
            }
            if (t != null){
                t.leave(pl);
                pl.sendMessage("LEAVED");
            }
            else{
                pl.sendMessage("ERROR!");
            }
        }
        if (args[0].equalsIgnoreCase("members")){
            Team t = bs.teammanager.GetTeamMem(pl);
            if (t == null){
                pl.sendMessage(ChatColor.RED + "팀이 없습니다");
                return true;
            }
            ArrayList<Player> pllist = t.getPlayers();
            pl.sendMessage(ChatColor.GREEN + "팀 내 플레이어: " + pllist.toString());
            return true;
        }
        if (args[0].equalsIgnoreCase("kick")){
            if (bs.teammanager.GetTeam(pl) == null){
                pl.sendMessage(ChatColor.RED + "팀이 없거나 팀장이 아닙니다");
                return true;
            }
            if (Bukkit.getPlayer(args[1]) == pl){
                pl.sendMessage(ChatColor.RED + "자기자신은 강퇴가 불가능합니다");
                return true;
            }
            if (args[1] == null){
                pl.sendMessage("해당 플레이어가 없습니다");
                return true;
            }
            Team t = bs.teammanager.GetTeamMem(pl);
            if (t == null){
                pl.sendMessage(ChatColor.RED + "팀이 없습니다");
                return true;
            }
            ArrayList<Player> pllist = t.getPlayers();
            if (!pllist.contains(Bukkit.getPlayer(args[1]))){
                pl.sendMessage("해당플레이어가 팀 내에 없습니다.");
                return true;
            }
            else{
                pllist.remove(Bukkit.getPlayer(args[1]));
                pl.sendMessage(ChatColor.RED + "해당 플레이어를 킥했습니다");
                Bukkit.getPlayer(args[1]).sendMessage(ChatColor.RED + "팀에서 강퇴당했습니다");
            }
        }
        if (args[0].equalsIgnoreCase("promote")){
            if (Bukkit.getPlayer(args[1]) == pl){
                pl.sendMessage(ChatColor.RED + "다른 사람을 지정해주세요");
                return true;
            }
            Player pll = null;
            try{
                pll = Bukkit.getPlayer(args[1]);
            }
            catch(Exception e){
                pl.sendMessage(ChatColor.RED + "해당 플레이어가 온라인이 아니거나 존재하지 않습니다");
                return true;
            }
            Team t = bs.teammanager.GetTeam(pl);
            Team newTeam = bs.teammanager.CreateTeam(pll);
            for(Player p: t.getPlayers()){
                newTeam.join(p);
            }
            pll.sendMessage(ChatColor.GREEN + "당신이 이제부터 파티장입니다");
            pl.sendMessage(ChatColor.GREEN + "파티장을 양도했습니다");
        }
        if (args[0].equalsIgnoreCase("start")){
            if (bs.teammanager.GetTeam(pl).getLeader() == null){
                pl.sendMessage("팀이없거나 파티장이 아닙니다");
                return true;
            }
            bs.gamemanager.GameStart(bs.teammanager.GetTeam(pl).getPlayers());
        }
        return true;
    }
}
