package swingy.model.character;

import swingy.utils.ActualAndMaxValuePair;
import swingy.utils.map.MapObjectType;

public abstract class Character {

    private Coordinate coordinate;

    private ActualAndMaxValuePair<Integer, Integer> hp;
    private ActualAndMaxValuePair<Integer, Integer> mp;

    private int attackStrength;
    private int defenceStrength;
    MapObjectType mapObjectType;

    public Character(MapObjectType mapObjectType) {
        this.mapObjectType = mapObjectType;
    }

    public ActualAndMaxValuePair<Integer, Integer> getHp() {
        return hp;
    }

    public void setHp(ActualAndMaxValuePair<Integer, Integer> hp) {
        this.hp = hp;
    }

    public ActualAndMaxValuePair<Integer, Integer> getMp() {
        return mp;
    }

    public void setMp(ActualAndMaxValuePair<Integer, Integer> mp) {
        this.mp = mp;
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

    public void printCharacteristics() {
        System.out.printf("hp == %s, mp == %s, attack == %s, defence == %s%n", getHp(), getHp(), getAttackStrength(), getDefenceStrength());
    }

}
