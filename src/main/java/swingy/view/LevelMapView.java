package swingy.view;

import swingy.model.character.Character;
import swingy.model.character.Coordinate;
import swingy.model.map.MapModel;
import swingy.utils.map.MapObjectType;

import java.util.Map;

public interface LevelMapView extends View{
    void printMap(MapModel mapModel);
}
