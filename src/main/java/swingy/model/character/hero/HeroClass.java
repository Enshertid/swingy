package swingy.model.character.hero;

public enum HeroClass {
    WARRIOR(0),
    THIEF(0);

    private final int id;

    public int getId() {
        return id;
    }

    HeroClass(int id) {
        this.id = id;
    }
}
