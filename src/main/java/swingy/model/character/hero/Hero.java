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
    private Integer id;
    private HeroClass heroClass;

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
        this.setId(null);
    }

    public Hero() {
        super(MapObjectType.HERO);
    }

    public Hero(HeroClass heroClass) {
        super(MapObjectType.HERO);
        this.curExperience = 0;
        this.setExpCupForLevel(1000);
        this.level = 1;
        if (heroClass.equals(heroClass.WARRIOR)) {
            this.setHp(10);
            this.setMaxHp(10);
            this.setAttackStrength(3);
            this.setDefenceStrength(5);
            this.setId(null);
        } else {
            this.setHp(10);
            this.setMaxHp(7);
            this.setAttackStrength(5);
            this.setDefenceStrength(3);
            this.setId(null);
        }
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

    public HeroClass getHeroClass() {
        return heroClass;
    }

    public void setHeroClass(HeroClass heroClass) {
        this.heroClass = heroClass;
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
        setMaxHp(getMaxHp() + 3);
        setHp(getMaxHp() + 3);
        setAttackStrength(getAttackStrength() + 2);
        setDefenceStrength(getDefenceStrength() + 2);
        setExpCupForLevel(getLevel() * 1000 + (getLevel()-1) * (getLevel() - 1) * 450);
        setCurExperience(0);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format(
                """
                        ==================HERO:==============
                        ===== NAME:%20s =====
                        ===== LEVEL:%19d =====
                        ===== CLASS:%19s =====
                        """,
                getName(),
                getLevel(),
                getHeroClass()
        );

    }
}
