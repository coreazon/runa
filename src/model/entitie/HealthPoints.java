package model.entitie;

public class HealthPoints {

    private int healthPoints;

    public HealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public void takeDamage(int damage) {
        healthPoints -= damage;
    }
}
