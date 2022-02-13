package swingy.utils.algorithms.random;

import swingy.model.character.Artifact;
import swingy.model.character.ArtifactType;
import swingy.model.character.Character;
import swingy.model.character.Coordinate;
import swingy.model.character.villain.Villain;
import swingy.utils.map.MapObjectType;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Randomizers {
    private final static Random randomizer = new Random();

    public static Map<Coordinate, Character> getVillainsDependingOnMapSizeAndHeroLevel(int mapSize, int level) {
        int numberOfVillains = level > 3 ? level - 1 : level;

        var retVal = new HashMap<Coordinate, Character>();

        for (int i = 0; i < numberOfVillains; i++) {
            var isUpperHalf = isUpperHalf();

            int x = setCoordinate(mapSize, isUpperHalf);
            int y = setCoordinate(mapSize, isUpperHalf);

            var coord = new Coordinate(x, y);

            while (retVal.get(coord) != null) {
                x = setCoordinate(mapSize, isUpperHalf);
                y = setCoordinate(mapSize, isUpperHalf);
                coord = new Coordinate(x, y);
            }

            var villain = new Villain();
            villain.setMapObjectType(MapObjectType.ENEMY);
            villain.setAttackStrength(randomCharacteristic(level, isSuperItem()));
            villain.setDefenceStrength(randomCharacteristic(level, isSuperItem()));
            villain.setHp(randomCharacteristic(level, isSuperItem()));
            villain.setExpForWin(level * 250);
            if (Randomizers.isSuperItem())
                villain.setArtifact(new Artifact(level > 3 ? level - 1 : level, false, ArtifactType.HELM));
            if (Randomizers.isSuperItem())
                villain.setArtifact(new Artifact(level > 3 ? level - 1 : level, false, ArtifactType.SWORD));
            if (Randomizers.isSuperItem())
                villain.setArtifact(new Artifact(level > 3 ? level - 1 : level, false, ArtifactType.ARMOR));
            retVal.put(coord, villain);
        }

        return retVal;

    }

    private static boolean isUpperHalf() {
        return randomizer.nextBoolean();
    }

    private static int setCoordinate(int mapSize, boolean isUpperHalf) {
        var half = mapSize / 2;
        if (isUpperHalf) {
            return randomizer.nextInt(mapSize - half) + half;
        } else {
            return randomizer.nextInt(half);
        }
    }

    public static boolean runOrFight() {
        return randomizer.nextBoolean();
    }

    public static boolean plusOrMinus() {
        return randomizer.nextBoolean();
    }

    public static int getOffset(int offset) {
        return randomizer.nextInt(offset);
    }

    public static int getArtifactCharacteristic(int itemLevel, boolean superItem) {
        if (superItem) {
            return randomizer.nextInt(itemLevel * 3);
        } else {
            return randomizer.nextInt(itemLevel * 2);
        }
    }

    public static boolean isSuperItem() {
        return randomizer.nextBoolean();
    }

    public static int isEnemyUseArtifact() {
        return randomizer.nextBoolean() ? 1 : 0;
    }

    private static int randomCharacteristic(int level, boolean isSuperCharacteristic) {
        if (isSuperCharacteristic) {
            return randomizer.nextInt((level + 1) * 3);
        } else {
            return randomizer.nextInt(level + 5);
        }
    }

    public static int getRandomDeath() {
        return randomizer.nextInt(10000);
    }
}
