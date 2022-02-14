package swingy.view;

import swingy.model.character.Artifact;
import swingy.model.character.hero.Hero;
import swingy.model.map.MapModel;

public interface LevelMapView extends View{
    void printMap(MapModel mapModel);
    void printFightDescription(MapModel mapModel, Hero character);
    void printFailedRun() throws InterruptedException;
    void printWonBattle() throws InterruptedException;
    void printSuccessRun() throws InterruptedException;
    void printWonGame() throws InterruptedException;
    void printArtifactDescription(Hero character, Artifact artifact);
    void printArtifactSuccessfulPickingUp();
    void printMessageAboutCleanMap() throws InterruptedException;
    void printLevelUp(int level, int maxLevel) throws InterruptedException;
}
