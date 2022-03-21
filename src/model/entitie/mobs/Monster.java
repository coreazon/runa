package model.entitie.mobs;

import model.entitie.HealthPoints;

public class Monster {

    private final HealthPoints healthPoints;


    public Monster(HealthPoints healthPoints) {
        this.healthPoints = healthPoints;
    }

    public HealthPoints getHealthPoints() {
        return healthPoints;
    }

    public void takeDamage(int damage){
        healthPoints.takeDamage(damage);
    }
}
