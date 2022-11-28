package util;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import zombiesurvival.zs.Main;

public class VisitToPlay {
    private Main bs= Main.getPlugin(Main.class);

    public void setSpawnLocation(Player p){
        Location loc = p.getLocation();
        String worldchangeName = bs.vw.getVisitedWorldGeneration().get(loc.getWorld().getName());
        bs.locationmanager.getConfig().set("location."+worldchangeName+".x", loc.getX());
        bs.locationmanager.getConfig().set("location."+worldchangeName+".y", loc.getY());
        bs.locationmanager.getConfig().set("location."+worldchangeName+".z", loc.getZ());
        p.sendMessage(ChatColor.GREEN + "Generated!");
        p.sendMessage(ChatColor.GREEN + worldchangeName + ", " + bs.locationmanager.getConfig().get("location."+worldchangeName+".x") + ", " + bs.locationmanager.getConfig().get("location."+worldchangeName + ".y") + ", " + bs.locationmanager.getConfig().get("location."+worldchangeName+".z"));
        bs.locationmanager.saveconfig();
    }
    public void setPos1(Player p, Location Loc1){
        String worldchangeName = bs.vw.getVisitedWorldGeneration().get(Loc1.getWorld().getName());
        bs.locationmanager.getConfig().set("pos1."+worldchangeName+".x", Loc1.getX());
        bs.locationmanager.getConfig().set("pos1."+worldchangeName+".y", Loc1.getY());
        bs.locationmanager.getConfig().set("pos1."+worldchangeName+".z", Loc1.getZ());
        p.sendMessage(ChatColor.GREEN + "Generated!");
        p.sendMessage(ChatColor.GREEN + worldchangeName + ", " + bs.locationmanager.getConfig().get("pos1."+worldchangeName+".x") + ", " + bs.locationmanager.getConfig().get("pos1."+worldchangeName + ".y") + ", " + bs.locationmanager.getConfig().get("pos1."+worldchangeName+".z"));
        bs.locationmanager.saveconfig();
    }
    public void setPos2(Player p, Location Loc2){
        String worldchangeName = bs.vw.getVisitedWorldGeneration().get(Loc2.getWorld().getName());
        bs.locationmanager.getConfig().set("pos2."+worldchangeName+".x", Loc2.getX());
        bs.locationmanager.getConfig().set("pos2."+worldchangeName+".y", Loc2.getY());
        bs.locationmanager.getConfig().set("pos2."+worldchangeName+".z", Loc2.getZ());
        p.sendMessage(ChatColor.GREEN + "Generated!");
        p.sendMessage(ChatColor.GREEN + worldchangeName + ", " + bs.locationmanager.getConfig().get("pos2."+worldchangeName+".x") + ", " + bs.locationmanager.getConfig().get("pos2."+worldchangeName + ".y") + ", " + bs.locationmanager.getConfig().get("pos2."+worldchangeName+".z"));
        bs.locationmanager.saveconfig();
    }
    public Location getLocation(String s, World w) {
        return (new Location(w, (double) bs.locationmanager.getConfig().get("location."+s+".x"), (double) bs.locationmanager.getConfig().get("location."+s+".y"), (double) bs.locationmanager.getConfig().get("location."+s+".z")));
    }
    public Location getPos1(String s, World w) {
        return (new Location(w, (double) bs.locationmanager.getConfig().get("pos1."+s+".x"), (double) bs.locationmanager.getConfig().get("pos1."+s+".y"), (double) bs.locationmanager.getConfig().get("pos1."+s+".z")));
    }
    public Location getPos2(String s, World w) {
        return (new Location(w, (double) bs.locationmanager.getConfig().get("pos2."+s+".x"), (double) bs.locationmanager.getConfig().get("pos2."+s+".y"), (double) bs.locationmanager.getConfig().get("pos2."+s+".z")));
    }
}
