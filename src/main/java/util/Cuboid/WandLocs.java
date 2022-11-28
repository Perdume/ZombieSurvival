package util.Cuboid;

import org.bukkit.Location;

public class WandLocs {
    private Location Loc1;
    private Location Loc2;
    public Location getLoc1(){
        return Loc1;
    }
    public Location getLoc2(){
        return Loc2;
    }
    public void setLoc1(Location l){
        Loc1 = l;
    }
    public void setLoc2(Location l){
        Loc2 = l;
    }
}
