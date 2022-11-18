package User;


import Arena.SubArena;
import Game.Game;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import zombiesurvival.zs.Main;

import java.util.UUID;

public class User {
    private UUID user;
    private boolean Spect = false;
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
