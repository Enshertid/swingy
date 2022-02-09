package swingy.model.map;

import swingy.model.character.Character;
import swingy.model.character.Coordinate;

public class MapModel {
    private final java.util.Map<Coordinate, Character> charactersOnMap;
    private int mapSize;

    public MapModel(java.util.Map<Coordinate, Character> charactersOnMap, int mapSize) {
        this.charactersOnMap = charactersOnMap;
        this.mapSize = mapSize;
    }

    public int getMapSize() {
        return mapSize;
    }

    public void addCharacterOnMap(Coordinate coordinate, Character character) {
        this.charactersOnMap.put(coordinate, character);
    }

    public void setMapSize(int mapSize) {
        this.mapSize = mapSize;
    }

    public void removeCharacterFromMap(Coordinate coordinate) {
        charactersOnMap.remove(coordinate);
    }

    public Character getCharacter(Coordinate coordinate) {
        return charactersOnMap.get(coordinate);
    }

    public void removeAllCharacters() {
        charactersOnMap.clear();
    }
}
