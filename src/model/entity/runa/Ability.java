package model.entity.runa;

import model.entity.Score;

import java.util.Objects;

/**
 * This class represents an ability
 *
 * @author urliz
 * @version 1.0
 */
public class Ability {

    private static final String FORMAT = "%s(%d)";
    private final Abilities ability;
    private final Score level;

    /**
     * Creates a new Ability
     *
     * @param ability the ability
     * @param level   the level
     */
    public Ability(Abilities ability, Score level) {
        this.ability = ability;
        this.level = level;
    }

    /**
     * returns the level of the ability
     *
     * @return teh leve
     */
    public Score getLevel() {
        return level;
    }

    /**
     * returns the ability
     *
     * @return the ability
     */
    public Abilities getAbility() {
        return ability;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ability ability1 = (Ability) o;
        return ability == ability1.ability && Objects.equals(level, ability1.level);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ability, level);
    }

    @Override
    public String toString() {
        return String.format(FORMAT, ability.getRepresentation(), level.getNumber());
    }
}
