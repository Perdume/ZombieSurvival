package Game;

import Arena.SubArena;
import User.User;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import zombiesurvival.zs.Main;

import java.util.List;

public class Game {
    private SubArena Area;
    private Main bs = Main.getPlugin(Main.class);
    private ScoreboardManager manager = Bukkit.getScoreboardManager();
    private Scoreboard board = manager.getNewScoreboard();
    public Game(SubArena area){
        this.Area = area;
    }
    public void GameStart(List<Player> li){
        //Game Start
        for (Player p: li){
            Area.join(p);
        }
        for (User p: Area.getPlayers()) {
            p.getPlayer().teleport(Area.getSpawnLocation());
        }
    }
    public void GameEnd(){
        // Game End
    }
    public void broadcast(String str){
        for (User p: Area.getPlayers()){
            p.getPlayer().sendMessage(str);
        }
    }
    private void updateScoreboard(Player player) {
        bs.sc.updatePerLine(board,"L3", "&eL3Changed", 3);
        bs.sc.updatePerLine(board,"L2", "&eL2Changed", 2);
        //Tip: bs.sc.updatePerLine(scoreboard,Current Line, Change Line, Line);//
    }

    private void createScoreboard(Player player) {
        bs.sc.SetTitle("&6&oNAME", player.getWorld().getName(), board);
        bs.sc.SetScore(4, "L1", player.getWorld().getName(), board);
        bs.sc.SetScore(3, "L2", player.getWorld().getName(), board);
        bs.sc.SetScore(2, "L3", player.getWorld().getName(), board);
        bs.sc.SetScore(1, "L4", player.getWorld().getName(), board);
        //Tip: bs.sc.SetScore(Line, msg, WorldName, scoreboard);//
    }

    public Boolean isPlayerisPlaying(Player p){
        for (User u: Area.getPlayers())
            if (u.getPlayer() == p){
                return true;
            }
        return false;
    }
    public SubArena getArena(){
        return Area;
    }
    public List<User> GetPlayers(){
        return Area.getPlayers();
    }

}
