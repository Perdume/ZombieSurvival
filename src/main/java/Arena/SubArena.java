package Arena;

import User.User;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import zombiesurvival.zs.Main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubArena {
    private Main bs = Main.getPlugin(Main.class);

    private String name;
    private Location spawnLocation;
    private Arena FAN;

    private List<User> players = new ArrayList<>();

    public SubArena(String nam, Location spawnLocation, Arena SelArena) {
        this.name = nam;
        this.spawnLocation = spawnLocation;
        FAN = SelArena;
    }

    public void join(Player p) {
        if (!players.contains(bs.usermanager.getUser(p.getUniqueId()))) {
            players.add(bs.usermanager.getUser(p.getUniqueId()));
        }
    }

    public void leave(Player p) {
        players.remove(bs.usermanager.getUser(p.getUniqueId()));
    }

    public List<User> getPlayers() {
        return players;
    }
    public void ClearPlayers() {
        players.clear();
    }
    public String getArenaName(){
        return name;
    }

    public Location getSpawnLocation() {
        return spawnLocation;
    }

    public Arena getArena(){
        return FAN;
    }

    public void setSpawnLocation(Location spawnLocation) {
        this.spawnLocation = spawnLocation;
    }


    public List<User> getSpectators(){
        List<User> spe = new ArrayList<>();
        for(User p: getPlayers()){
            if (p.isSpectator()){
                if (spe.isEmpty()) {
                    List<User> temp = new ArrayList<>(Arrays.asList(p));
                    spe = temp;
                }
                else{
                    spe.add(p);
                }
            }
        }
        return spe;
    }


}