package util.Cuboid;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

public class Tool {
    public static ItemStack getTool(){
        ItemStack it = new ItemStack(Material.NETHERITE_SWORD);
        ItemMeta imt = it.getItemMeta();
        imt.setDisplayName(ChatColor.RED.toString() + ChatColor.BOLD + "EDIT TOOL");
        imt.setLore(Collections.singletonList(ChatColor.GREEN + "ELSE?"));
        it.setItemMeta(imt);
        return it;
    }
}
