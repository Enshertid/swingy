package swingy.view.console;

import swingy.model.character.Character;
import swingy.model.character.Coordinate;
import swingy.model.map.MapModel;
import swingy.utils.map.MapObjectType;
import swingy.view.LevelMapView;

import java.util.Map;

public class LevelMapConsoleView extends ConsoleView implements LevelMapView {
    public LevelMapConsoleView() {
    }

    @Override
    public void printMap(MapModel mapModel) {
        var currentCoordinate = new Coordinate(0,0);

        for (int i = 0; i < mapModel.getMapSize(); i++) {
            currentCoordinate.setY(i);
            System.out.println();
            for (int j = 0; j < mapModel.getMapSize(); j++) {
                currentCoordinate.setX(j);

                if (mapModel.getCharacter(currentCoordinate) == null) {
                    printPosition(MapObjectType.EMPTY_SPACE);
                } else {
                    printPosition(mapModel.getCharacter(currentCoordinate).getMapObjectType());
                }
            }
        }
    }

    private void printPosition(MapObjectType objectType) {
        if (objectType.equals(MapObjectType.HERO)) {
            System.out.print("H");
        } else if (objectType.equals(MapObjectType.ENEMY)) {
            System.out.print("X");
        } else {
            System.out.print("O");
        }
    }

}
