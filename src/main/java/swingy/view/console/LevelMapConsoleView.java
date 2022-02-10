package swingy.view.console;

import swingy.model.character.Artifact;
import swingy.model.character.Character;
import swingy.model.character.Coordinate;
import swingy.model.character.hero.Hero;
import swingy.model.map.MapModel;
import swingy.utils.FightResult;
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

    @Override
    public void printArtifactDescription(Hero character, Artifact artifact) {
        if (character.getArtifact() != null) {
            System.out.println("you already have artifact: ");
            System.out.println(character.getArtifact());
        }
        System.out.println("you found new Artifact: ");
        System.out.println(artifact);
        if (character.getArtifact() != null) {
            System.out.println("do you want to replace it?");
        } else {
            System.out.println("do you want to take artifact?");
        }
        System.out.println("print yes or no");
    }

    @Override
    public void printArtifactSuccessfulPickingUp() {
        System.out.println("artifact are picked up!");
    }

    @Override
    public void printFightDescription(MapModel mapModel, Hero character) {
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
            System.out.print("O");
        }
    }

    @Override
    public void printFailedRun() throws InterruptedException {
        System.out.println("YOU TRY TO RUN, BUT YOU FAILED, BATTLE BEGINS");
        Thread.sleep(1000);
    }

    @Override
    public void printWonBattle() throws InterruptedException {
        System.out.println("YOU WIN BATTLE");
        Thread.sleep(2000);
    }

    @Override
    public void printSuccessRun() throws InterruptedException {
        System.out.println("YOU ARE SUCCESSFUL RUNs");
        Thread.sleep(2000);
    }

    @Override
    public void printWonGame() throws InterruptedException {
        System.out.println("YOU WON GAME, CONGRATULATIONS!");
        Thread.sleep(2000);
    }
}
