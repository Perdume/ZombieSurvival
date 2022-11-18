package zombiesurvival.zs;

import Manager.*;
import org.bukkit.plugin.java.JavaPlugin;
import util.ScoreHelper;
import util.VisitWorld;
import util.WorldEditor;
import util.WorldLoader;

public final class Main extends JavaPlugin {

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

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
