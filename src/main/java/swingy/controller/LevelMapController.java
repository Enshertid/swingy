package swingy.controller;

import swingy.model.character.Artifact;
import swingy.model.character.Coordinate;
import swingy.model.character.hero.Hero;
import swingy.model.map.MapModel;
import swingy.utils.ActionResult;
import swingy.utils.ViewMode;
import swingy.utils.algorithms.RunAlgo;
import swingy.utils.algorithms.fight.FightAlgo;
import swingy.utils.algorithms.random.Randomizers;
import swingy.view.console.ConsoleButtonPressHandler;
import swingy.utils.exceptions.BreakGameFromKeyboardException;
import swingy.utils.exceptions.LooseGameException;
import swingy.view.LevelMapView;
import swingy.view.console.LevelMapConsoleView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

import static swingy.Game.mainConfig;

public class LevelMapController {

    private ConsoleButtonPressHandler buttonPressHandler;
    private LevelMapView levelMapView;
    private final MapModel mapModel;

    public LevelMapController() {
        mapModel = new MapModel(new HashMap<>(), 0);
        ViewMode viewMode = mainConfig.getViewMode();
        if (viewMode.equals(ViewMode.CONSOLE)) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            levelMapView = new LevelMapConsoleView();
            buttonPressHandler = new ConsoleButtonPressHandler(bufferedReader);
        }
    }

    public void gameCycle(Hero character) throws LooseGameException, IOException, BreakGameFromKeyboardException, InterruptedException {
        boolean isLevelWon = true;
        while (character.getLevel() < character.getMaxLevel()) {
            if (isLevelWon) {
                reloadMap(character.getLevel());
                character.setCoordinate(new Coordinate(mapModel.getMapSize() / 2, mapModel.getMapSize() / 2));
                isLevelWon = false;
            }

            printMap(character);
            var result = handleButtonPressings(character);

            if (result == ActionResult.GAME_WON) {
                levelMapView.printWonGame();
                System.exit(0);
                // won game
            } else if (result == ActionResult.LEVEL_WON) {
                isLevelWon = true;
                if (mapModel.isMapEmpty()) {
                    levelMapView.printMessageAboutCleanMap();
                    if (character.updateExperienceAndUpLevelIfCup(250)) {
                        levelMapView.printLevelUp(character.getLevel(), character.getMaxLevel());
                    }
                }
            }
            clearScreen();
        }
    }

    private void reloadMap(int level) {
        if (level < 7)
            mapModel.setMapSize((level - 1) * 5 + 10);
        else
            mapModel.setMapSize(39);
        mapModel.removeAllCharacters();
        for (var entry : Randomizers.getVillainsDependingOnMapSizeAndHeroLevel(mapModel.getMapSize(), level).entrySet()) {
            mapModel.addCharacterOnMap(entry.getKey(), entry.getValue());
        }
    }

    private void printMap(Hero character) {
        mapModel.addCharacterOnMap(character.getCoordinate(), character);
        levelMapView.printMap(mapModel);
    }

    private ActionResult handleButtonPressings(Hero character) throws IOException, BreakGameFromKeyboardException, LooseGameException, InterruptedException {
        var result = buttonPressHandler.handleMapMoveClick(character, mapModel);
        if (result.equals(ActionResult.MEET_ENEMY)) {
           result = battleHandling(character);
        }
        return result;
    }

    private ActionResult battleHandling(Hero character) throws IOException, BreakGameFromKeyboardException, InterruptedException, LooseGameException {
        clearScreen();
        levelMapView.printFightDescription(mapModel, character);
        var choiceRunOfFight = buttonPressHandler.choiceRunOrFight(character, mapModel);
        clearScreen();

        if ((choiceRunOfFight.equals(ActionResult.TRY_TO_RUN) && !Randomizers.runOrFight()) || (choiceRunOfFight.equals(ActionResult.BATTLE_START))) {
            if (choiceRunOfFight.equals(ActionResult.TRY_TO_RUN)) {
                levelMapView.printFailedRun();
            }
            if (FightAlgo.fight(character, mapModel.getCharacter(character.getCoordinate()))) {
                levelMapView.printLevelUp(character.getLevel(), character.getMaxLevel());
            }
            artifactAlgo(character);
            levelMapView.printWonBattle();
            mapModel.removeCharacterFromMap(character.getCoordinate());
        } else {
            RunAlgo.run(character, mapModel);
            levelMapView.printSuccessRun();
        }
        return ActionResult.NOTHING_IMPORTANT;
    }

    private void artifactAlgo(Hero character) throws IOException, BreakGameFromKeyboardException {
        var armor = mapModel.getCharacter(character.getCoordinate()).getArmor();
        var helm = mapModel.getCharacter(character.getCoordinate()).getHelm();
        var sword = mapModel.getCharacter(character.getCoordinate()).getSword();
        artifactHandling(armor, character);
        artifactHandling(helm, character);
        artifactHandling(sword, character);
    }

    private void artifactHandling(Artifact artifact, Hero character) throws IOException, BreakGameFromKeyboardException {
        if (artifact != null) {
            levelMapView.printArtifactDescription(character, artifact);
            ActionResult actionResult = buttonPressHandler.choiceTakeArtifactOrNot(character, artifact);
            if (actionResult.equals(ActionResult.PICK_UP_ARTIFACT)) {
                character.setArtifact(artifact);
                levelMapView.printArtifactSuccessfulPickingUp();
            }
        }
    }


    private void clearScreen() {
        levelMapView.clean();
    }
}
