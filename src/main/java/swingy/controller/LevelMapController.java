package swingy.controller;

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
    private Randomizers randomizers;
    private MapModel mapModel;

    public LevelMapController() {
        mapModel = new MapModel(new HashMap<>(), 0);
        ViewMode viewMode = mainConfig.getViewMode();
        if (viewMode.equals(ViewMode.CONSOLE)) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            levelMapView = new LevelMapConsoleView();
            buttonPressHandler = new ConsoleButtonPressHandler(bufferedReader);
        }
        this.randomizers = new Randomizers();
    }

    public void gameCycle(Hero character) throws LooseGameException, IOException, BreakGameFromKeyboardException, InterruptedException {
        boolean isLevelWon = true;
        while (character.getLevel() < 15) {
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
                continue;
            }
            clearScreen();
        }
    }

    private void reloadMap(int level) {
        mapModel.setMapSize((level - 1) * 5 + 9);
        mapModel.removeAllCharacters();
        for (var entry : randomizers.getVillainsDependingOnMapSizeAndHeroLevel(mapModel.getMapSize(), level).entrySet()) {
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

        if ((choiceRunOfFight.equals(ActionResult.TRY_TO_RUN) && !randomizers.runOrFight()) || (choiceRunOfFight.equals(ActionResult.BATTLE_START))) {
            if (choiceRunOfFight.equals(ActionResult.TRY_TO_RUN)) {
                levelMapView.printFailedRun();
            }
            FightAlgo.fight(character, mapModel.getCharacter(character.getCoordinate()));
            artifactHandling(character);
            levelMapView.printWonBattle();
            mapModel.removeCharacterFromMap(character.getCoordinate());
        } else {
            RunAlgo.run(character, mapModel);
            levelMapView.printSuccessRun();
        }
        return ActionResult.NOTHING_IMPORTANT;
    }

    private void artifactHandling(Hero character) throws IOException, BreakGameFromKeyboardException {
        var artifact = mapModel.getCharacter(character.getCoordinate()).getArtifact();
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
