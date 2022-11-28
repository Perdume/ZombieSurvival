package util.Cuboid;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import zombiesurvival.zs.Main;

public class EvH implements Listener {
    private Main bs = Main.getPlugin(Main.class);
    @EventHandler
    public void Clicker(PlayerInteractEvent e){
        if (e.getClickedBlock() == null){
            return;
        }
        if (e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(Tool.getTool().getItemMeta().getDisplayName())) {
            if (e.getAction() == Action.LEFT_CLICK_BLOCK) {
                bs.wlm.getWandLocs(e.getPlayer()).setLoc1(e.getClickedBlock().getLocation());
                e.getPlayer().sendMessage(ChatColor.LIGHT_PURPLE + "SET POS1 TO " + e.getClickedBlock().getLocation());
            }
            if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                bs.wlm.getWandLocs(e.getPlayer()).setLoc2(e.getClickedBlock().getLocation());
                e.getPlayer().sendMessage(ChatColor.LIGHT_PURPLE + "SET POS2 TO " + e.getClickedBlock().getLocation());
            }
        }
    }
}
