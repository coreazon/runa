package model.entity.mobs;

import model.entity.HealthPoints;
import model.entity.Score;

/**
 * The interface is used to calculate damage
 *
 * @author urliz
 * @version 1.0
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
