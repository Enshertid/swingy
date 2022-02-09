package swingy.model.character.hero;

public enum HeroClass {
    MAGE(1),
    WARRIOR(2),
    THIEF(3);

    private final int id;

    public int getId() {
        return id;
    }

    HeroClass(int id) {
        this.id = id;
    }
}
