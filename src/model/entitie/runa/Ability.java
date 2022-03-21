package model.entitie.runa;

import model.entitie.Score;

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
}
