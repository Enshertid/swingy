package swingy.model.character;

import swingy.utils.map.MapObjectType;

public abstract class Character {

    private Coordinate coordinate;

    private Artifact artifact;

    private int expForWin;

    private int hp;
    private int maxHp;

    private int attackStrength;
    private int defenceStrength;
    MapObjectType mapObjectType;

    public Character(MapObjectType mapObjectType) {
        this.mapObjectType = mapObjectType;
    }

    public int getExpForWin() {
        return expForWin;
    }

    public void setExpForWin(int expForWin) {
        this.expForWin = expForWin;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAttackStrength() {
        return attackStrength;
    }

    public void setAttackStrength(int attackStrength) {
        this.attackStrength = attackStrength;
    }

    public int getDefenceStrength() {
        return defenceStrength;
    }

    public void setDefenceStrength(int defenceStrength) {
        this.defenceStrength = defenceStrength;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public MapObjectType getMapObjectType() {
        return mapObjectType;
    }

    public void setMapObjectType(MapObjectType mapObjectType) {
        this.mapObjectType = mapObjectType;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public void printCharacteristics() {
        System.out.printf("hp == %s, mp == %s, attack == %s, defence == %s%n", getHp(), getHp(), getAttackStrength(), getDefenceStrength());
    }

    public Artifact getArtifact() {
        return artifact;
    }

    public void setArtifact(Artifact artifact) {
        this.artifact = artifact;
    }
}
