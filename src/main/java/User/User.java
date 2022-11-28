package User;


import Arena.SubArena;
import Game.Game;
import User.Ability.Ab1.AbList;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import zombiesurvival.zs.Main;

import java.util.UUID;

public class User {
    private UUID user;
    private boolean Spect = false;
    private AbList ab;
    private int coin = 0;
    private int armorlevel = 0;
    private int swordlevel = 0;
    private int armorenchant = 0;
    private int swordenchant = 0;
    private Main bs = Main.getPlugin(Main.class);
    public User(UUID uid){
        this.user = uid;
    }

    public User(Player player){
        this.user = player.getUniqueId();
    }
    public SubArena getArena(){
        for(String name: bs.subarenaManager.getArenas()){
            assert bs.subarenaManager.getArena(name).getPlayers() != null;
            if(bs.subarenaManager.getArena(name).getPlayers().contains(bs.usermanager.getUser(this.user))){
                return bs.subarenaManager.getArena(name);
            }
        }
        return null;
    }
    public AbList getAbility(){
        return ab;
    }
    public int getArmorLevel(){
        return armorlevel;
    }
    public void setArmorLevel(int l){
        armorlevel = l;
    }
    public int getSwordLevel(){
        return swordlevel;
    }
    public void setSwordLevel(int l){
        swordlevel = l;
    }
    public int getArmorEnchantLevel(){
        return armorenchant;
    }
    public void setArmorEnchantLevel(int l){
        armorenchant = l;
    }
    public int getSwordEnchantLevel(){
        return swordenchant;
    }
    public void setSwordEnchantLevel(int l){
        swordenchant = l;
    }
    public int getCoin(){
        return coin;
    }
    public void setCoin(int co){
        coin = co;
    }
    public void setAbility(AbList ab){
        this.ab =ab;
    }
    public Game getGame(){
        for(Game game: bs.gamemanager.getGames()){
            if(game.GetPlayers() == null){
                return null;
            }
            if(game.GetPlayers().contains(bs.usermanager.getUser(this.user))){
                return game;
            }
        }
        return null;
    }
    public UUID getUniqueId() {
        return user;
    }
    public Player getPlayer(){
        return Bukkit.getPlayer(user);
    }
    public Boolean isSpectator() {
        return Spect;
    }
    public void setSpectator(Boolean bool){
        Spect = bool;
    }
}
