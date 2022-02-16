package swingy.view.console;

import swingy.controller.LevelMapController;
import swingy.model.character.Artifact;
import swingy.model.character.Coordinate;
import swingy.model.character.hero.Hero;
import swingy.model.map.MapModel;
import swingy.utils.exceptions.BreakGameFromKeyboardException;
import swingy.utils.exceptions.GameWonException;
import swingy.utils.exceptions.LooseGameException;
import swingy.utils.map.MapObjectType;
import swingy.view.LevelMapView;

import java.io.IOException;

public class LevelMapConsoleView extends ConsoleView implements LevelMapView {
    private LevelMapController levelMapController;
    public LevelMapConsoleView() {
    }

    @Override
    public void printMap(MapModel mapModel, Hero character, LevelMapController levelMapController) throws IOException, BreakGameFromKeyboardException, LooseGameException, InterruptedException, GameWonException {
        this.levelMapController = levelMapController;
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
        levelMapController.handleButtonPressings(character, null);
    }

    @Override
    public void printArtifactDescription(Hero character, Artifact artifact) {
        if (character.getArtifactOfCurrentType(artifact.getArtifactType()) != null) {
            System.out.println("you already have: " + artifact.getArtifactType().toString());
            System.out.println(character.getArtifactOfCurrentType(artifact.getArtifactType()));
        }
        System.out.println("you found new Artifact: ");
        System.out.println(artifact);
        if (character.getArtifactOfCurrentType(artifact.getArtifactType()) != null) {
            System.out.println("do you want to replace it?");
        } else {
            System.out.println("do you want to take artifact?");
        }
        System.out.println("print Y or N");
    }

    @Override
    public void printArtifactSuccessfulPickingUp() {
        System.out.println("artifact are picked up!");
    }

    @Override
    public void printFightDescription(MapModel mapModel, Hero character) {
        levelMapController.clearScreen();
        System.out.println("you meet some enemy this is his characteristics");
        mapModel.getCharacter(character.getCoordinate()).printCharacteristics();
        System.out.println("yours characteristics is");
        character.printCharacteristics();
        System.out.println("do you want to fight, or try to run");
        System.out.println("press R for run, or press F for pay resp.. fight");
    }

    private void printPosition(MapObjectType objectType) {
        if (objectType.equals(MapObjectType.HERO)) {
            System.out.print("H");
        } else if (objectType.equals(MapObjectType.ENEMY)) {
            System.out.print("X");
        } else {
            System.out.print("o");
        }
        System.out.print(" ");
    }

    @Override
    public void printFailedRun() throws InterruptedException {
        levelMapController.clearScreen();
        System.out.println("YOU TRY TO RUN, BUT YOU FAILED, BATTLE BEGINS");
        Thread.sleep(1000);
    }

    @Override
    public void printWonBattle() throws InterruptedException {
        levelMapController.clearScreen();
        System.out.println("YOU WIN BATTLE");
        Thread.sleep(2000);
    }

    @Override
    public void printSuccessRun() throws InterruptedException {
        levelMapController.clearScreen();
        System.out.println("YOU ARE SUCCESSFUL RUNs");
        Thread.sleep(2000);
    }

    @Override
    public void printWonGame() throws InterruptedException {
        levelMapController.clearScreen();
        System.out.println("YOU WON GAME, CONGRATULATIONS!");
        Thread.sleep(2000);
    }

    @Override
    public void printMessageAboutCleanMap() throws InterruptedException {
        levelMapController.clearScreen();
        System.out.println("you clean map, and get bonus 250 experience for that!");
        Thread.sleep(2000);
    }

    @Override
    public void printLevelUp(int level, int maxLevel) throws InterruptedException {
        levelMapController.clearScreen();
        System.out.println("your current level is " + level + " for won this game, up level to " + maxLevel);
        Thread.sleep(2000);
    }
}
