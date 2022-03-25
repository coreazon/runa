package model.entity.mobs;

import model.entity.HealthPoints;
import model.entity.Score;

public interface DamageCalculatorMob {

    abstract HealthPoints calculateDamage(Score level);

}
