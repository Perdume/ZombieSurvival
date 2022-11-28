package User.Ability.Ab1;

import User.Ability.Ab1.Skills.Archer;
import User.Ability.Ab1.Skills.Attacker;
import User.Ability.Ab1.Skills.Healer;
import User.Ability.Ab1.Skills.Summoner;
import org.bukkit.entity.Player;

public class Ability {
    Player p;
    public Ability(Player pl){
        p = pl;
    }
    public void UseAbility(AbList ab){
        if (ab == AbList.Attacker){
            Attacker.UseSkill(p);
        }
        else if (ab == AbList.Archer){
            Archer.UseSkill(p);
        }
        else if (ab == AbList.Healer){
            Healer.UseSkill(p);
        }
        else if (ab == AbList.Summoner){
            Summoner.UseSkill(p);
        }

    }
}
