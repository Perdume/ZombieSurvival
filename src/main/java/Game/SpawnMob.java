package Game;

import io.lumine.mythic.api.mobs.MythicMob;
import io.lumine.mythic.bukkit.BukkitAdapter;
import io.lumine.mythic.bukkit.MythicBukkit;
import io.lumine.mythic.core.mobs.ActiveMob;

import javax.swing.text.html.parser.Entity;
import javax.xml.stream.Location;

public enum SpawnMob {
    Plain("Zo1L1", "Zo1L1", "Zo1L1", "Zo1L1"),
    Dessert("Zo1L1", "Zo1L1", "Zo1L1", "Zo1L1");

    private String zombie;
    private String skeleton;
    private String spider;
    private String creeper;
    SpawnMob(String zombie, String skeleton, String spider, String creeper) {
        this.zombie = zombie;
        this.skeleton = skeleton;
        this.spider = spider;
        this.creeper = creeper;
    }
    public String getZombie(){
        return zombie;
    }
    public String getSkeleton(){
        return skeleton;
    }
    public String getSpider(){
        return spider;
    }
    public String getCreeper(){
        return creeper;
    }
}
