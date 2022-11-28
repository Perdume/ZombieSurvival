package zombiesurvival.zs;

import Command.admin;
import Command.user;
import Game.Eh;
import Manager.*;
import Shop.ShopGUI;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import util.Cuboid.EvH;
import util.ScoreHelper;
import util.VisitWorld;
import util.WorldEditor;
import util.WorldLoader;

public final class Main extends JavaPlugin implements Listener {

    public ArenaManager arenaManager;
    public SubArenaManager subarenaManager;
    public WorldManager worldManager;
    public UserManager usermanager;
    public GameManager gamemanager;
    public LocationManager locationmanager;
    public VisitWorld vw;
    public WorldLoader wl;
    public WorldEditor we;
    public ScoreHelper sc;
    public TeamManager teammanager;
    public WandLocManager wlm;
    public ShopGUI sg;

    @Override
    public void onEnable() {
        // Plugin startup logic
        subarenaManager = new SubArenaManager();
        arenaManager = new ArenaManager();
        this.worldManager = new WorldManager(this);
        this.usermanager = new UserManager(this);
        this.gamemanager = new GameManager(this);
        locationmanager = new LocationManager(this);
        vw = new VisitWorld(this);
        wl = new WorldLoader();
        we = new WorldEditor();
        sc = new ScoreHelper(this);
        teammanager = new TeamManager(this);
        wlm = new WandLocManager(this);
        sg = new ShopGUI();
        Bukkit.getServer().getPluginManager().registerEvents(wl, this);
        Bukkit.getServer().getPluginManager().registerEvents(new EvH(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new Eh(), this);
        Bukkit.getServer().getPluginManager().registerEvents(sg, this);
        Bukkit.getServer().getPluginManager().registerEvents(we, this);
        this.getCommand("zs").setExecutor(new user());
        this.getCommand("zsadmin").setExecutor(new admin());
        arenaManager.arealoader();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
