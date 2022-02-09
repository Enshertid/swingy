package swingy.utils.algorithms.random;

import swingy.model.character.Character;
import swingy.model.character.Coordinate;
import swingy.model.character.villain.Villain;
import swingy.utils.map.MapObjectType;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Randomizers {
    private final Random randomizer = new Random();

    public Map<Coordinate, Character> getVillainsDependingOnMapSizeAndHeroLevel(int mapSize, int level) {
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
            retVal.put(coord,villain);
        }

        return retVal;

    }

    private boolean isUpperHalf() {
        return randomizer.nextBoolean();
    }

    private int setCoordinate(int mapSize, boolean isUpperHalf) {
        var half = mapSize / 2;
        if (isUpperHalf) {
            return randomizer.nextInt(mapSize - half) + half;
        } else {
            return randomizer.nextInt(half);
        }
    }

    public boolean runOrFight() {
        return randomizer.nextBoolean();
    }

    public boolean plusOrMinus() {
        return randomizer.nextBoolean();
    }

    public int getOffset() {
        return randomizer.nextInt(3);
    }
}
