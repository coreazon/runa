package model.entitie.mobs;

import model.entitie.FocusPoints;
import model.entitie.HealthPoints;

public class Monster {

    private final HealthPoints healthPoints;
    private MonsterCard focusCard;
    private final FocusPoints focusPoints;

    public Monster(HealthPoints healthPoints, FocusPoints focusPoints) {
        this.healthPoints = healthPoints;
        this.focusCard = null;
        this.focusPoints = focusPoints;
    }

    public FocusPoints getFocusPoints() {
        return focusPoints;
    }

    public MonsterCard getFocusCard() {
        return focusCard;
    }

    public void setFocusCard(MonsterCard focusCard) {
        this.focusCard = focusCard;
    }

    public HealthPoints getHealthPoints() {
        return healthPoints;
    }

    public void takeDamage(int damage){
        healthPoints.takeDamage(damage);
    }
}
