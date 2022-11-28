package Game;

import Arena.SubArena;
import Shop.ShopGUI;
import User.User;
import net.minecraft.world.entity.EntityCreature;
import net.minecraft.world.entity.EntityInsentient;
import net.minecraft.world.level.pathfinder.PathEntity;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.EquipmentSlot;
import zombiesurvival.zs.Main;

import java.util.Random;

public class Eh implements Listener {
    private Main bs = Main.getPlugin(Main.class);

    @EventHandler
    public void Target(EntityTargetEvent e) {
        if (e.getTarget() instanceof Player) {
            Player p = (Player) e.getTarget();
            User u = bs.usermanager.getUser(p);
            if (bs.gamemanager.PlayerIsPlaying(p)) {
                if (u.isSpectator()) {
                    e.setCancelled(true);
                    e.setTarget(getRandomTarget(e.getEntity().getWorld()));
                }
            }
        }
        if (e.getTarget() instanceof Monster){
            e.setCancelled(true);
        }
    }
    public Player getRandomTarget(World w){
        Game g = bs.gamemanager.getGame(bs.subarenaManager.getArena(w.toString()));
        return g.GetPlayers().get(new Random().nextInt(g.GetPlayers().size())).getPlayer();
    }

    @EventHandler
    public void PlayerDeathEvent(EntityDamageEvent el){
        /*
        Case
        1. entity -> Player
        2. Player -> Entity
        3. Unknown -> Player
        4. Unknown -> Entity
        5. Entity -> Entity
        6. Player -> Player
        */
        if (el.getEntity() instanceof Player){
            Player pl = (Player) el.getEntity();
            if (el instanceof EntityDamageByEntityEvent){
                EntityDamageByEntityEvent e = (EntityDamageByEntityEvent) el;
                if (e.getDamager() instanceof Player) {
                    //PLAYER -> PLAYER;
                }
                else{
                    //ENTITY -> PLAYER;
                }
                if (pl.getHealth() < 0){
                    bs.usermanager.getUser(pl).setSpectator(true);
                    pl.setCollidable(false);
                    if (bs.gamemanager.getGame(bs.gamemanager.PlayingArena(pl)).getPlayerList() == 0){
                        bs.gamemanager.getGame(bs.gamemanager.PlayingArena(pl)).GameEnd();
                    }
                }
            }
            else {
                EntityDamageEvent e = el;
                // UNKNOWN -> PLAYER;
            }
        }
        else {
            if (el instanceof EntityDamageByEntityEvent){
                EntityDamageByEntityEvent e = (EntityDamageByEntityEvent) el;
                if (e.getDamager() instanceof Player) {
                    //PLAYER -> ENTITY;
                    Entity en = e.getEntity();
                    if (en.isDead()) {
                        Player p = (Player) e.getDamager();
                        bs.usermanager.getUser(p).setCoin(bs.usermanager.getUser(p).getCoin() + 20);
                    }
                }
                else{
                    //ENTITY -> ENTITY;
                    if (e.getDamager() instanceof Monster&&e.getEntity() instanceof Monster){
                        e.setCancelled(true);
                    }
                }
            }
            else {
                EntityDamageEvent e = el;
                // UNKNOWN -> ENTITY;
            }
        }
    }
    @EventHandler
    public void OpenShop(PlayerInteractEvent el){
        if(el.getHand() == EquipmentSlot.OFF_HAND) return;
        if (el.getAction() == Action.RIGHT_CLICK_AIR ||el.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (el.getPlayer().getInventory().getItemInMainHand().getType() == Material.BOOK) {
                bs.sg.openInventory(el.getPlayer());
            }
        }
    }
    @EventHandler
    public void QuitGame(PlayerQuitEvent e){
        if (bs.gamemanager.PlayerIsPlaying(e.getPlayer())){
            bs.usermanager.getUser(e.getPlayer()).setSpectator(true);
            SubArena area = bs.gamemanager.PlayingArena(e.getPlayer());
            if (bs.gamemanager.getGame(area).getPlayerList() == 0){
                bs.gamemanager.getGame(area).GameEnd();
            }
        }
    }
}
