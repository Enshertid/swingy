package swingy.model.character.hero;

import swingy.model.character.Character;
import swingy.model.character.Coordinate;
import swingy.model.map.MapModel;
import swingy.utils.ActionResult;
import swingy.utils.algorithms.random.Randomizers;
import swingy.utils.map.MapObjectType;

public class Hero extends Character {
    private int level;
    private String name;
    private int curExperience;
    private int maxLevel;
    private final Randomizers random = new Randomizers();

    public Hero(String name) {
        super(MapObjectType.HERO);
        this.name = name;
        this.curExperience = 0;
        this.level = 1;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCurExperience() {
        return curExperience;
    }

    public void setCurExperience(int curExperience) {
        this.curExperience = curExperience;
    }

    public boolean isMaxLevel() {
        return level == maxLevel;
    }

    public Coordinate runningAway(MapModel map, int offset) {
        return getNewCoordinate(offset);
    }

    public ActionResult characterStatusAfterChangeCoordinate(MapModel mapModel, Coordinate coordinate) {

        if (coordinate.getX() < 0
                || coordinate.getY() < 0
                || coordinate.getX() > mapModel.getMapSize()
                || coordinate.getY() > mapModel.getMapSize() ){
            return isMaxLevel() ? ActionResult.GAME_WON : ActionResult.LEVEL_WON;
        } else if (mapModel.getCharacter(getCoordinate()) != null) {
            return ActionResult.MEET_ENEMY;
        } else {
            return ActionResult.NOTHING_IMPORTANT;
        }

    }

    private Coordinate getNewCoordinate(int offset) {
        boolean offsetSightX = random.plusOrMinus();
        boolean offsetSightY = random.plusOrMinus();
        int offsetY = random.getOffset() + 1;
        int offsetX = random.getOffset() + 1;

        var currentCoordinate = getCoordinate();
        var newCoordinate = new Coordinate(0,0);
        if (offsetSightX) {
            newCoordinate.setX(currentCoordinate.getX() + offsetX);
        } else {
            newCoordinate.setX(currentCoordinate.getX() - offsetX);
        }
        if (offsetSightY) {
            newCoordinate.setY(currentCoordinate.getY() + offsetY);
        } else {
            newCoordinate.setY(currentCoordinate.getY() - offsetY);
        }
        return newCoordinate;
    }
}
