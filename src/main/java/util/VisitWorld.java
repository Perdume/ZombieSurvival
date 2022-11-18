package util;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import zombiesurvival.zs.Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.bukkit.Bukkit.getServer;

public class VisitWorld {
    private Main bs;
    private List<World> VisitedWorldList = new ArrayList<>();
    private HashMap<String, String> GenWorld = new HashMap<>(); // 1. GenedName 2. OriginName
    public VisitWorld(Main bw){
        bs = bw;
    }

    public HashMap<String, String> getVisitedWorldGeneration(){
        return GenWorld;
    }
    public List<World> getVisitedWorldList(){
        return VisitedWorldList;
    }
    public void addVisitedWorldList(String S, World World){
        VisitedWorldList.add(World);
        GenWorld.put(World.getName(), S);
    }

    public void remove(World w){
        for (Player p: w.getPlayers()){
            p.teleport((Location) bs.worldManager.getConfig().get("endloc"));
        }
        GenWorld.remove(w.getName());
        if(getServer().unloadWorld(w, false)){
            WorldManage.deleteFilesRecursively(w.getWorldFolder());
        }
    }

}
