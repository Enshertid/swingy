package swingy.model.character;

import swingy.utils.algorithms.random.Randomizers;

public class Artifact {
    private final int attackBonus;
    private final int defenceBonus;
    private final int hpBonus;
    private final boolean isEmpty;

    public Artifact(int itemLevel, boolean isEmpty) {
        if (!isEmpty) {
            boolean isSuperItem = Randomizers.isSuperItem();
            attackBonus = Randomizers.getArtifactCharacteristic(itemLevel, isSuperItem);
            defenceBonus = Randomizers.getArtifactCharacteristic(itemLevel, isSuperItem);
            hpBonus = Randomizers.getArtifactCharacteristic(itemLevel, isSuperItem);
            this.isEmpty = false;
        } else {
            attackBonus = 0;
            defenceBonus = 0;
            hpBonus = 0;
            this.isEmpty = true;
        }
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public int getAttackBonus() {
        return attackBonus;
    }

    public int getDefenceBonus() {
        return defenceBonus;
    }

    public int getHpBonus() {
        return hpBonus;
    }

    @Override
    public String toString() {
        return "Artifact characteristics: " +
                " attack bonus: " + attackBonus +
                " defence bonus: " + defenceBonus +
                " hp bonus: " + hpBonus;
    }
}
