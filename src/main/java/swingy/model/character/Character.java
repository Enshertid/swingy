package swingy.model.character;

import swingy.utils.map.MapObjectType;

import java.util.List;

public abstract class Character {

    private Coordinate coordinate;

    private Artifact armor;

    private Artifact sword;

    private Artifact helm;

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

    public Artifact getArmor() {
        return armor;
    }

    public Artifact getSword() {
        return sword;
    }

    public Artifact getHelm() {
        return helm;
    }

    public void setArtifact(Artifact artifact) {
        if (artifact.getArtifactType() == ArtifactType.ARMOR) {
            armor = artifact;
        } else if (artifact.getArtifactType() == ArtifactType.HELM) {
            helm = artifact;
        } else if (artifact.getArtifactType() == ArtifactType.SWORD) {
            sword = artifact;
        }
    }

    public void setEmptyArtifacts() {
        if (helm == null) {
            helm = new Artifact(0, true, ArtifactType.HELM);
        }
        if (sword == null) {
            sword = new Artifact(0, true, ArtifactType.SWORD);
        }
        if (armor == null) {
            armor = new Artifact(0, true, ArtifactType.ARMOR);
        }
    }

    public void resetEmptyArtifacts() {
        if (helm.isEmpty()) {
            helm = null;
        }
        if (sword.isEmpty()) {
            sword = null;
        }
        if (armor.isEmpty()) {
            armor = null;
        }
    }

    public Artifact getArtifactOfCurrentType(ArtifactType artifactType) {
        if (artifactType == ArtifactType.SWORD) {
            return sword;
        }
        if (artifactType == ArtifactType.HELM) {
            return helm;
        }
        return armor;
    }
}
