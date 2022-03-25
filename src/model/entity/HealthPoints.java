package model.entity;

public class HealthPoints {

    private int healthPoints;

    public HealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void heal(int healthPoints) {
        this.healthPoints += healthPoints;
    }

    public void takeDamage(HealthPoints damage) {
        healthPoints -= damage.getHealthPoints();
    }

    public void shieldDamage(HealthPoints shield) {
        healthPoints -= shield.getHealthPoints();
        if (healthPoints < 0) healthPoints = 0;
    }
}
