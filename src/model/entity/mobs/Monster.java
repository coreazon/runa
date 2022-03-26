package model.entity.mobs;

import model.entity.FocusPoints;
import model.entity.HealthPoints;
import model.entity.runa.AbilityType;

import java.util.List;

public class Monster {

    private final HealthPoints healthPoints;
    private final List<MonsterCard> cards;
    private final Type type;
    private final FocusPoints focusPoints;
    private final String name;
    private MonsterCard focusCard;
    private MonsterCard defenseCard;
    private static final String TO_STRING_FORMAT = "%s (%d HP, %d FP): attempts %s next";

    public Monster(String name, HealthPoints healthPoints, FocusPoints focusPoints, List<MonsterCard> cards, Type type) {
        this.healthPoints = healthPoints;
        this.focusCard = null;
        this.type = type;
        this.defenseCard = null;
        this.focusPoints = focusPoints;
        this.cards = cards;
        this.name = name;
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

    public void setDefenseCard(MonsterCard defenseCard) {
        this.defenseCard = defenseCard;
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

    public void takeDamage(HealthPoints damage, AbilityType type) {
        if (defenseCard != null) {
            if (defenseCard.getCard().getDefense() == type)
                damage.shieldDamage(defenseCard.getCard().calculateDamage(defenseCard.getLevel()));
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

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format(TO_STRING_FORMAT, getName()
                , getHealthPoints().getHealthPoints()
                , getFocusPoints().getFocusPoints()
                , getCards().get(0).toString());
    }
}
