package Manager;


import Arena.SubArena;
import org.bukkit.Location;
import zombiesurvival.zs.Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// NOT THREAD SAFE!
public class SubArenaManager {
    private Map<String, SubArena> arenas = new HashMap<>();
    private ArrayList<String> areaname = new ArrayList<>();
    private Main bs = Main.getPlugin(Main.class);

    public SubArena registerArena(String name, Location spawnLocation) {
        SubArena newSub = new SubArena(name, spawnLocation);
        areaname.add(name);
        arenas.put(name, newSub);
        return newSub;
    }

    public void remove(String name) {
        arenas.remove(name);
        areaname.remove(name);
    }

    public SubArena getArena(String name) {
        return arenas.get(name);
    }
    public List<String> getArenas() {
        return areaname;
    }

    public boolean exists(String name) {
        return arenas.get(name) != null;
    }

}
