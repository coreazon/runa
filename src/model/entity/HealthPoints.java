package model.entity;

/**
 * This class represents HealthPoints
 *
 * @author urliz
 * @version 1.0
 */
public class HealthPoints {

    private int healthPoints;

    /**
     * Creates a new HealthPoints
     *
     * @param healthPoints healthPoints
     */
    public HealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    /**
     * returns the healthPoints
     *
     * @return healthPoints
     */
    public int getHealthPoints() {
        return healthPoints;
    }

    /**
     * heals
     *
     * @param healthPoints the healthPoints
     */
    public void heal(int healthPoints) {
        this.healthPoints += healthPoints;
    }

    /**
     * take damage
     *
     * @param damage teh damage taken
     */
    public void takeDamage(HealthPoints damage) {
        healthPoints -= damage.getHealthPoints();
    }

    /**
     * shields damage
     *
     * @param shield teh shield
     */
    public void shieldDamage(HealthPoints shield) {
        healthPoints -= shield.getHealthPoints();
        if (healthPoints < 0) healthPoints = 0;
    }
}
