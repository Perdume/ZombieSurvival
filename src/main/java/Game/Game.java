package Game;

import Arena.SubArena;
import User.User;
import io.lumine.mythic.api.mobs.MythicMob;
import io.lumine.mythic.bukkit.BukkitAdapter;
import io.lumine.mythic.bukkit.MythicBukkit;
import io.lumine.mythic.core.mobs.ActiveMob;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import util.Cuboid.Cuboid;
import util.VisitToPlay;
import zombiesurvival.zs.Main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {
    private SubArena Area;
    private Main bs = Main.getPlugin(Main.class);
    private ScoreboardManager manager = Bukkit.getScoreboardManager();
    private Scoreboard board = manager.getNewScoreboard();
    private int Stage = 0;
    private SpawnMob Concep;
    private Boolean isRunning = true;
    private int ZombieCount = 0;
    private int SkeletonCount = 0;
    private int SpiderCount = 0;
    private int Creepercount = 0;
    public Game(SubArena area){
        this.Area = area;
    }
    public void GameStart(List<Player> li){
        //Game Start
        for (Player p: li){
            Area.join(p);
            createScoreboard(p);
            bs.usermanager.getUser(p).setCoin(200);
        }
        ItemStack item = new ItemStack(Material.BOOK, 1);
        ItemMeta itm = item.getItemMeta();
        itm.setDisplayName(ChatColor.DARK_PURPLE + "상점");
        item.setItemMeta(itm);
        for (User p: Area.getPlayers()) {
            p.getPlayer().teleport(Area.getSpawnLocation());
            p.getPlayer().getInventory().addItem(item);
            p.setArmorLevel(0);
            p.setSwordLevel(0);
            p.setSwordEnchantLevel(0);
            p.setArmorEnchantLevel(0);
            p.setSpectator(false);
            bs.sc.jointeam(p.getPlayer(), board);
        }
        broadcast("30초후 게임이 시작됩니다");
        new BukkitRunnable() {
            int i = 31;
            @Override
            public void run() {
                i--;
                if (i<6&&i>0){
                    String str = ChatColor.RED + String.valueOf(i);
                    broadcast(str);
                }
                if (i == 0) {
                    String str = ChatColor.GREEN + "Start";
                    broadcast(str);
                    Checkmon();
                    cancel();
                }
            }
        }.runTaskTimer(bs, 0L, 20L);
    }

    private SpawnMob getRandomMonsterConcep(){
        ArrayList<SpawnMob> CpList = new ArrayList<>(Arrays.asList(SpawnMob.Plain, SpawnMob.Dessert));
        int Pll = CpList.size();
        int Plll = (int) (Pll * Math.random());
        return CpList.get(Plll);
    }
    public void Checkmon(){
        new BukkitRunnable() {
            @Override
            public void run() {
                if (isRunning){
                    for (User u: Area.getPlayers()){
                        updateScoreboard(u.getPlayer());
                    }
                    if (getEntityList() == 0) {
                        if (Stage % 5 == 0) {
                            Concep = getRandomMonsterConcep();
                            broadcast("스테이지 컨셉이" + Concep + "으로 변경되었습니다");
                        }
                        Stage++;
                        //ADD LEVEL
                        if (Stage % 5 == 0) {
                            ZombieCount = 0;
                            SkeletonCount = 0;
                            SpiderCount = 0;
                            Creepercount = 0;
                        }
                        AddLevel();
                        for (int n = 0; n < ZombieCount; n++) {
                            RandomSpawn(Concep.getZombie());
                        }
                        for (int n = 0; n < SkeletonCount; n++) {
                            RandomSpawn(Concep.getSkeleton());
                        }
                        for (int n = 0; n < SpiderCount; n++) {
                            RandomSpawn(Concep.getSpider());
                        }
                        for (int n = 0; n < Creepercount; n++) {
                            RandomSpawn(Concep.getCreeper());
                        }
                    }
                }
                else{
                    cancel();
                }
            }
        }.runTaskTimer(bs, 0, 20L);
    }
    private void RandomSpawn(String Et){
        Location loc = getRandomLocation();
        if (loc == null){
            broadcast("LOCATION FIND ERROR!");
        }
        MythicMob mob = MythicBukkit.inst().getMobManager().getMythicMob(Et).orElse(null);
        Location spawnLocation = loc;
        if(mob != null){
            mob.spawn(BukkitAdapter.adapt(spawnLocation),1);
        }
    }
    private Location getRandomLocation(){
        VisitToPlay vtp = new VisitToPlay();
        Location pos1 = vtp.getPos1(Area.getArena().getArenaName(), Area.getSpawnLocation().getWorld());
        Location pos2 = vtp.getPos2(Area.getArena().getArenaName(), Area.getSpawnLocation().getWorld());
        Cuboid cb = new Cuboid(pos1,pos2);
        for (int n=0;n<1000;n++){
            Location SpawnPos = cb.getRandomLocationwithoutY().add(0, 1, 0);
            Location l1 = SpawnPos.add(0, 1, 0);
            Location l2 = SpawnPos.add(0, -1, 0);
            while (cb.isIn(SpawnPos)){
                if (SpawnPos.getBlock().getType() == Material.AIR&&l1.getBlock().getType() == Material.AIR&&!l2.getBlock().getType().equals(Material.BARRIER)){
                    return SpawnPos;
                }
                else{
                    SpawnPos = SpawnPos.add(0, 1, 0);
                }
            }
        }
        return null;
    }
    private int getEntityList(){
        int count = 0;
        for(Entity i: Area.getSpawnLocation().getWorld().getEntities()){
            if (i.getType() != EntityType.PLAYER && i.getType() != EntityType.ARROW){
                count++;
            }
        }
        return count;
    }
    public int getPlayerList(){
        int count = 0;
        for(User p: Area.getPlayers()){
            if (!p.isSpectator()){
                count++;
            }
        }
        return count;
    }
    private void AddLevel(){
        if (Math.random() < 0.5){
            if (Math.random() < 0.5){
                if (Math.random() < 0.5){
                    SpiderCount++;
                }
                else{
                    Creepercount++;
                }
            }
            else{
                SkeletonCount++;
            }
        }
        else{
            ZombieCount++;
        }

    }
    public void GameEnd(){
        // Game End
        for (User u: Area.getPlayers()){
            Player p = u.getPlayer();
            u.getPlayer().teleport((Location) bs.worldManager.getConfig().get("endloc"));
            Area.leave(u.getPlayer());
            bs.usermanager.removeuser(p);
        }
        for (Entity i: Area.getSpawnLocation().getWorld().getEntities()){
            if (i.getType() != EntityType.PLAYER){
                i.remove();
            }
        }
        isRunning = false;

    }
    public void broadcast(String str){
        for (User p: Area.getPlayers()){
            p.getPlayer().sendMessage(str);
        }
    }
    private void updateScoreboard(Player player) {
        bs.sc.updatePerLine(board,"코인개수: ", "코인개수: " + bs.usermanager.getUser(player).getCoin(), 3);
        bs.sc.updatePerLine(board,"남은 몬스터: ", "남은 몬스터: " + getEntityList(), 2);
        //Tip: bs.sc.updatePerLine(scoreboard,Current Line, Change Line, Line);//
    }

    private void createScoreboard(Player player) {
        bs.sc.SetTitle("&6&oZombieSurvival", player.getWorld().getName(), board);
        bs.sc.SetScore(4, "남은 플레이어: " + getPlayerList() , player.getWorld().getName(), board);
        bs.sc.SetScore(3, "코인개수: " + bs.usermanager.getUser(player).getCoin(), player.getWorld().getName(), board);
        bs.sc.SetScore(2, "남은 몬스터: " + getEntityList(), player.getWorld().getName(), board);
        //Tip: bs.sc.SetScore(Line, msg, WorldName, scoreboard);//
    }

    public Boolean isPlayerisPlaying(Player p){
        for (User u: Area.getPlayers())
            if (u.getPlayer() == p){
                return true;
            }
        return false;
    }
    public SubArena getArena(){
        return Area;
    }
    public List<User> GetPlayers(){
        return Area.getPlayers();
    }

}
