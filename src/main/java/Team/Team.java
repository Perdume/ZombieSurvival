package Team;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

public class Team {
    private UUID Leader;
    private ArrayList<Player> players;
    public Team(UUID uk){
        Leader = uk;
        players = new ArrayList<>(Arrays.asList(Bukkit.getPlayer(uk)));
    }
    public ArrayList<Player> getPlayers(){
        return players;
    }
    public Player getLeader(){
        return Bukkit.getPlayer(Leader);
    }
    public Boolean join(Player p){
        if(!players.contains(p)){
            players.add(p);
            return true;
        }
        else{
            return false;
        }
    }
    public Boolean leave(Player p){
        if(players.contains(p)){
            players.add(p);
            return true;
        }
        else {
            return false;
        }
    }

}
