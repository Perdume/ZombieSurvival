package Manager;

import Arena.SubArena;
import User.User;
import org.bukkit.entity.Player;
import zombiesurvival.zs.Main;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserManager {
    private Main bs;
    private final List<User> users = new ArrayList<>();
    public UserManager(Main plugin){
        this.bs = plugin;
    }
    public User getUser(Player player){
        for(User user: users){
            if (user.getUniqueId() == player.getUniqueId()){
                return user;
            }
        }
        User user = new User(player.getUniqueId());
        users.add(user);
        return user;
    }
    public User getUser(UUID player){
        for(User user: users){
            if (user.getUniqueId() == player){
                return user;
            }
        }
        User user = new User(player);
        users.add(user);
        return user;
    }
    public List<User> getUsers(SubArena arena) {
        return arena.getPlayers();
    }
    public void removeuser(Player player){
        users.remove(player);
    }
    public void removeuser(UUID user){
        users.remove(user);
    }
}
