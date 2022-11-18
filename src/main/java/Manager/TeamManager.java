package Manager;

import Team.Team;
import org.bukkit.entity.Player;
import zombiesurvival.zs.Main;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

public class TeamManager {
    private Main bs;
    private ArrayList<Team> TeamList = new ArrayList<>(Collections.emptyList());
    public TeamManager(Main pl){
        bs = pl;
    }
    public void CreateTeam(Player p){
        Team nt = new Team(p.getUniqueId());
        TeamList.add(nt);
    }
}
