package Manager;

import org.bukkit.entity.Player;
import util.Cuboid.WandLocs;
import zombiesurvival.zs.Main;

import java.util.HashMap;

public class WandLocManager {
    private Main plugin;
    private HashMap<Player, WandLocs> wd = new HashMap<>();
    public WandLocManager(Main bw){
        plugin = bw;
    }
    public WandLocs getWandLocs(Player pl){
        if (wd.get(pl) != null){
            return wd.get(pl);
        }
        else{
            WandLocs wl = new WandLocs();
            wd.put(pl, wl);
            return wl;
        }
    }

}
