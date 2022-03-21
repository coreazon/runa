package model.entity.mobs;

import model.entity.AttackType;
import model.entity.FocusPoints;
import model.entity.HealthPoints;

import java.util.List;

public class Monster {

    private final HealthPoints healthPoints;
    private MonsterCard focusCard;
    private MonsterCard defenseCard;
    private final List<MonsterCard> cards;
    private final FocusPoints focusPoints;

    public Monster(HealthPoints healthPoints, FocusPoints focusPoints, List<MonsterCard> cards) {
        this.healthPoints = healthPoints;
        this.focusCard = null;
        this.defenseCard = null;
        this.focusPoints = focusPoints;
        this.cards = cards;
    }

    public FocusPoints getFocusPoints() {
        return focusPoints;
    }

    public MonsterCard getDefenseCard() {
        return defenseCard;
    }

    public List<MonsterCard> getCards() {
        return cards;
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

    public void setDefenseCard(MonsterCard defenseCard) {
        this.defenseCard = defenseCard;
    }

    public void takeDamage(HealthPoints damage){
        healthPoints.takeDamage(damage);
    }

    public HealthPoints attack() {
        var card = cards.remove(0);
        HealthPoints damage;
        //code here
        if (card.getCard().getAttackType() == AttackType.DEFENSE){
            setDefenseCard(card);
            damage = new HealthPoints(0);
        }
        else if () {

        }

        cards.add(card);
        return damage;
    }
}
