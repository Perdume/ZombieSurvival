package Team;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class Team {
    private UUID Leader;
    private ArrayList<Player> players = new ArrayList<>();
    public Team(UUID uk){
        Leader = uk;
    }
    public ArrayList<Player> getPlayers(){
        return players;
    }
    public Player getLeader(){
        return Bukkit.getPlayer(Leader);
    }
    public void join(Player p){
        if(!players.contains(p)){
            players.add(p);
            p.sendMessage("added");
        }
        else{
            p.sendMessage("failed");
        }
    }
    public void leave(Player p){
        if(players.contains(p)){
            players.add(p);
            p.sendMessage("removed");
        }
        else{
            p.sendMessage("failed");
        }
    }

}
