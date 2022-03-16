package model.entitie.mobs;

public enum Mobs {

    FROG("Frog"),
    GHOST("Ghost"),
    GORGON("Gorgon"),
    SKELETON("Skeleton"),
    SPIDER("Spide"),
    GOBLIN("Goblin"),
    RAT("Rat"),
    MUSHROOMLIN("Mushroomlin"),
    SNAKE("Snake"),
    DARK_ELF("Dark Elf"),
    SHADOW_BLADE("Shadow Blade"),
    HORNET("Hornet"),
    TARANTULA("Tarantula"),
    BEAR("Bear"),
    MUSHROOMLON("Mushroomlon"),
    WILD_BOAR("Wild Boar");

    private final String representation;


    Mobs(String representation) {
        this.representation = representation;
    }

    public String getRepresentation() {
        return representation;
    }
}
