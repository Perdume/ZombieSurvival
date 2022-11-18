package Manager;

import Arena.Arena;
import Arena.SubArena;
import Game.Game;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;
import util.VisitToPlay;
import util.WorldManage;
import zombiesurvival.zs.Main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class GameManager{
    private Main bs;
    private Map<SubArena, Game> games = new HashMap<>();
    private List<Game> gamelist = new ArrayList<>();
    private Map<Arena, List<SubArena>> Arenalist = new HashMap<>();
    public GameManager(Main plugin){
        bs = plugin;
    }
    public Game getGame(SubArena area){
        return games.get(area);
    }
    public void registergame(SubArena area){
        games.put(area, new Game(area));

    }
    public List<Game> getGames(){
        return gamelist;
    }

    public void GameStart(List<Player> li){
        SubArena sb = GetRandomSubArena();
        Game gm = new Game(sb);
        games.put(sb, gm);
        gm.GameStart(li);
    }
    public void GameEnd(SubArena area){
        games.get(area).GameEnd();
        games.remove(area);
    }

    private SubArena GetRandomSubArena(){
        try {
            List<String> Areas = bs.arenaManager.getArenas();
            int RandomInt = (int) (Math.random() * (Areas.size() + 1) - 1);
            Arena SelArena = bs.arenaManager.getArena(Areas.get(RandomInt));
            File fi = SelArena.getWorldLoader();
            String MixedName = "Bowshot--PLAY--" + Randomname();
            Path toFolder = Paths.get(Bukkit.getWorldContainer().getAbsolutePath() + "\\" + MixedName);
            WorldManage wrma = new WorldManage();
            wrma.copyWorld(fi, toFolder.toFile());
            Files.move(toFolder, toFolder.resolveSibling(MixedName));
            WorldCreator wrm1 = new WorldCreator(MixedName);
            wrm1.generator("VoidGenerator");
            wrm1.createWorld();
            VisitToPlay vtp = new VisitToPlay();
            Location Loc = vtp.getLocation(fi.getName(), Bukkit.getWorld(MixedName));
            SubArena NewSubArena = bs.subarenaManager.registerArena(MixedName, Loc);
            return NewSubArena;
        }
        catch(IOException e){
            return null;
        }
    }
    public void autodeleteManager(){
        Bukkit.getScheduler().scheduleSyncRepeatingTask(bs, new Runnable() {
            @Override
            public void run() {
                for (World w: Bukkit.getWorlds()){
                    if (w.getName().contains("--PLAY--")){
                        if(w.getPlayers().size() == 0){
                            Bukkit.unloadWorld(w, false);
                            WorldManage.deleteFilesRecursively(w.getWorldFolder());
                        }
                    }
                    if (w.getName().contains("--VISIT--")){
                        if(w.getPlayers().size() == 0){
                            Bukkit.unloadWorld(w, false);
                            WorldManage.deleteFilesRecursively(w.getWorldFolder());
                        }
                    }
                }
                for(File f: bs.getDataFolder().listFiles()){
                    if (f.isDirectory()){
                        if (f.getName().contains("--")){
                            if (!ismatch(f)){
                                WorldManage.deleteFilesRecursively(f);
                            }
                        }
                    }
                }
            }
        }, 20, 1200);
    }
    public Boolean PlayerIsPlaying(Player p){
        if (gamelist == null) return false;
        for (Game g: gamelist){
            if (g.isPlayerisPlaying(p)){
                return true;
            }
        }
        return false;
    }
    private Boolean ismatch(File f){
        for (Game g: bs.gamemanager.getGames()){
            if (Objects.equals(g.getArena().getArenaName(), f.getName())){
                return true;
            }
        }
        return false;
    }
    private String Randomname(){
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit,rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }
}
