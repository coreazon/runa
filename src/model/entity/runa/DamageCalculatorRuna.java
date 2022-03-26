package model.entity.runa;

import model.dice.Dice;
import model.entity.FocusPoints;
import model.entity.HealthPoints;
import model.entity.Score;
import model.entity.mobs.Type;

public interface DamageCalculatorRuna {

    abstract HealthPoints calculateDamage(Score level, Score dice, FocusPoints focusPoints, Type targetType);
}
