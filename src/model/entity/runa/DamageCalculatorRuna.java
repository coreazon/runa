package model.entity.runa;

import model.entity.FocusPoint;
import model.entity.HealthPoints;
import model.entity.Score;
import model.entity.mobs.Type;

/**
 * this interface calculates damage
 */
public interface DamageCalculatorRuna {
    /**
     * calculates the damage
     *
     * @param level       the level
     * @param dice        the dice roll
     * @param focusPoints the fp points
     * @param targetType  the target type
     * @return the damage
     */
    HealthPoints calculateDamage(Score level, Score dice, FocusPoint focusPoints, Type targetType);
}
