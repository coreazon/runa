package model.entitie;

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
}
