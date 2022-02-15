package swingy.view;

import swingy.controller.LevelMapController;
import swingy.model.character.Artifact;
import swingy.model.character.hero.Hero;
import swingy.model.map.MapModel;
import swingy.utils.exceptions.BreakGameFromKeyboardException;
import swingy.utils.exceptions.LooseGameException;

import java.io.IOException;

public interface LevelMapView extends View{
    void printMap(MapModel mapModel, Hero character, LevelMapController levelMapController) throws IOException, BreakGameFromKeyboardException, LooseGameException, InterruptedException;
    void printFightDescription(MapModel mapModel, Hero character) throws IOException, BreakGameFromKeyboardException;
    void printFailedRun() throws InterruptedException;
    void printWonBattle() throws InterruptedException;
    void printSuccessRun() throws InterruptedException;
    void printWonGame() throws InterruptedException;
    void printArtifactDescription(Hero character, Artifact artifact) throws IOException, BreakGameFromKeyboardException;
    void printArtifactSuccessfulPickingUp();
    void printMessageAboutCleanMap() throws InterruptedException;
    void printLevelUp(int level, int maxLevel) throws InterruptedException;
}
