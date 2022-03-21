package model.entity.runa;

import model.entity.Score;

import java.util.Objects;

public class Ability {

    private final Abilities ability;
    private final Score level;

    public Ability(Abilities ability, Score level) {
        this.ability = ability;
        this.level = level;
    }

    public Score getLevel() {
        return level;
    }

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
}
