package Arena;

import zombiesurvival.zs.Main;

import java.io.File;

public class Arena {
    private Main zs = Main.getPlugin(Main.class);

    private String name;
    private String filename;


    public Arena(String nam, String filename) {
        this.name = nam;
        this.filename = filename;
    }
    public String getArenaName(){
        return name;
    }
    public File getWorldLoader(){
        File fi = new File(zs.getDataFolder().getAbsolutePath() + "\\WorldList\\" + filename);
        return fi;
    }
    public String getfl(){
        return filename;
    }




}