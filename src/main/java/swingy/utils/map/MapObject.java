package swingy.utils.map;

import swingy.model.character.Coordinate;

public interface MapObject {
    Coordinate getCoordinates();
    MapObjectType getType();
}
