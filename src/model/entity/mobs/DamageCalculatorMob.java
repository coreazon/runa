package model.entity.mobs;

import model.entity.HealthPoints;
import model.entity.Score;

/**
 * The interface is used to calculate damage
 */
public interface DamageCalculatorMob {
    /**
     * calculates the damage
     *
     * @param level the level of an ability
     * @return the damage
     */
    HealthPoints calculateDamage(Score level);

}
