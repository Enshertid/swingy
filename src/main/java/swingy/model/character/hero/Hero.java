package swingy.model.character.hero;

import swingy.model.character.Character;
import swingy.model.character.Coordinate;
import swingy.model.map.MapModel;
import swingy.utils.ActionResult;
import swingy.utils.algorithms.random.Randomizers;
import swingy.utils.map.MapObjectType;

public class Hero extends Character {
    private short level;
    private String name;
    private int curExperience;
    private int expCupForLevel;
    private int maxLevel;
    private int id;

    public Hero(String name) {
        super(MapObjectType.HERO);
        this.name = name;
        this.curExperience = 0;
        this.setExpCupForLevel(1000);
        this.level = 1;
        this.setHp(10);
        this.setMaxHp(10);
        this.setAttackStrength(5);
        this.setDefenceStrength(5);
    }

    public int getExpCupForLevel() {
        return expCupForLevel;
    }

    public void setExpCupForLevel(int expCupForLevel) {
        this.expCupForLevel = expCupForLevel;
    }

    public boolean updateExperienceAndUpLevelIfCup(int bonusExp) {
        this.curExperience += bonusExp;
        if (curExperience >= expCupForLevel) {
            upLevel();
            return true;
        } else {
            return false;
        }
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public void setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
    }

    public short getLevel() {
        return level;
    }

    public void setLevel(short level) {
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

        setCoordinate(coordinate);
        if (coordinate.getX() < 0
                || coordinate.getY() < 0
                || coordinate.getX() >= mapModel.getMapSize()
                || coordinate.getY() >= mapModel.getMapSize() ){
            return isMaxLevel() ? ActionResult.GAME_WON : ActionResult.LEVEL_WON;
        } else if (mapModel.getCharacter(getCoordinate()) != null) {
            return ActionResult.MEET_ENEMY;
        } else {
            return ActionResult.NOTHING_IMPORTANT;
        }

    }

    private Coordinate getNewCoordinate(int offset) {
        boolean offsetSightX = Randomizers.plusOrMinus();
        boolean offsetSightY = Randomizers.plusOrMinus();
        int offsetY = Randomizers.getOffset(offset);
        int offsetX = Randomizers.getOffset(offset);

        var currentCoordinate = getCoordinate();
        var newCoordinate = new Coordinate(0,0);
        if (offsetSightX) {
            newCoordinate.setX(currentCoordinate.getX() + offsetX + 1);
        } else {
            newCoordinate.setX(currentCoordinate.getX() - offsetX - 1);
        }
        if (offsetSightY) {
            newCoordinate.setY(currentCoordinate.getY() + offsetY + 1);
        } else {
            newCoordinate.setY(currentCoordinate.getY() - offsetY - 1);
        }
        return newCoordinate;
    }

    public void upLevel() {
        setLevel((short) (getLevel() + 1));
        setHp(getHp() + 10);
        setMaxHp(getMaxHp() + 10);
        setAttackStrength(getAttackStrength() + 5);
        setDefenceStrength(getDefenceStrength() + 5);
        setExpCupForLevel(getLevel() * 1000 + (getLevel()-1) * (getLevel() - 1) * 450);
        setCurExperience(0);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
