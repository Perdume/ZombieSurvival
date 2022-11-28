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
    public Team CreateTeam(Player p){
        Team nt = new Team(p.getUniqueId());
        TeamList.add(nt);
        return nt;
    }
    public Team GetTeam(Player p) {
        for (Team t : TeamList) {
            if (t.getLeader() == p) {
                return t;
            }
        }
        return null;
    }
    public Team GetTeamMem(Player p) {
        for (Team t : TeamList) {
            if (t.getPlayers().contains(p)) {
                return t;
            }
        }
        return null;
    }
    public Boolean isPlayerInTeam(Player p) {
        if (GetTeamMem(p) != null){
            return true;
        }
        else{
            return false;
        }
    }
}
