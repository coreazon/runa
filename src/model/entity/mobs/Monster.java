package model.entity.mobs;

import model.entity.AttackType;
import model.entity.FocusPoints;
import model.entity.HealthPoints;
import model.entity.runa.AbilityType;

import java.util.List;

public class Monster {

    private final HealthPoints healthPoints;
    private MonsterCard focusCard;
    private MonsterCard defenseCard;
    private final List<MonsterCard> cards;
    private final Type type;
    private final FocusPoints focusPoints;

    public Monster(HealthPoints healthPoints, FocusPoints focusPoints, List<MonsterCard> cards, Type type) {
        this.healthPoints = healthPoints;
        this.focusCard = null;
        this.type = type;
        this.defenseCard = null;
        this.focusPoints = focusPoints;
        this.cards = cards;
    }

    public Type getType() {
        return type;
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

    public void takeDamage(HealthPoints damage, AbilityType type){
        if (defenseCard != null) {
            if (defenseCard.getCard().getDefense() == type) damage.shieldDamage(defenseCard.getCard().calculateDamage(defenseCard.getLevel()));
            this.defenseCard = null;
        }
        healthPoints.takeDamage(damage);
    }

    public void takeDirectHit(HealthPoints damage) {
        healthPoints.takeDamage(damage);
    }

    public void reduceFocusPoints(MonsterCard card) {
        if (card.getCard().getType() != AbilityType.MAGICAL) return;
        this.focusPoints.setFocusPoints(getFocusPoints().getFocusPoints() - card.getLevel().getNumber());
    }
}
