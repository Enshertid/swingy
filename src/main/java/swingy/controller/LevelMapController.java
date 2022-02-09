package swingy.controller;

import jdk.swing.interop.SwingInterOpUtils;
import swingy.model.character.Coordinate;
import swingy.model.character.hero.Hero;
import swingy.model.map.MapModel;
import swingy.utils.ActionResult;
import swingy.utils.ViewMode;
import swingy.utils.algorithms.fight.FightAlgo;
import swingy.utils.algorithms.random.Randomizers;
import swingy.utils.button.console.ButtonPressHandler;
import swingy.utils.button.console.fight.ButtonPressFightHandler;
import swingy.utils.button.console.map.ButtonPressMapHandler;
import swingy.utils.exceptions.BreakGameFromKeyboardException;
import swingy.utils.exceptions.LooseGameException;
import swingy.utils.exceptions.WonGameException;
import swingy.view.LevelMapView;
import swingy.view.console.LevelMapConsoleView;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

import static swingy.Game.mainConfig;

public class LevelMapController {

    private ButtonPressHandler buttonPressMapHandler;
    private LevelMapView levelMapView;
    private Randomizers randomizers;
    private MapModel mapModel;

    public LevelMapController() {
        mapModel = new MapModel(new HashMap<>(), 0);
        ViewMode viewMode = mainConfig.getViewMode();
        if (viewMode.equals(ViewMode.CONSOLE)) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            levelMapView = new LevelMapConsoleView();
            buttonPressMapHandler = new ButtonPressMapHandler(bufferedReader);
        }
        this.randomizers = new Randomizers();
    }

    public void gameCycle(Hero character) throws WonGameException, LooseGameException, IOException, BreakGameFromKeyboardException {
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
                System.out.println("you won game, congrats!");
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

    private ActionResult handleButtonPressings(Hero character) throws IOException, BreakGameFromKeyboardException, WonGameException, LooseGameException {
        var result = buttonPressMapHandler.handleButtonClick(character, mapModel);

        if (result.equals(ActionResult.MEET_ENEMY)) {
            clearScreen();
            System.out.println("you meet some enemy this is his characteristics");
            mapModel.getCharacter(character.getCoordinate()).printCharacteristics();
            System.out.println("yours characteristics is");
            character.printCharacteristics();
            System.out.println("do you want to fight, or try to run");
            System.out.println("press R for run, or press F for pay resp.. fight");
            var choiceRunOfFight = buttonPressMapHandler.handleButtonClick(character, mapModel);
            if (
                    (choiceRunOfFight.equals(ActionResult.TRY_TO_RUN) && !randomizers.runOrFight())
                            ||
                    (choiceRunOfFight.equals(ActionResult.BATTLE_START))) {
                System.out.println("FIGHT");
                FightAlgo.fight(character, mapModel.getCharacter(character.getCoordinate()));
                result = ActionResult.NOTHING_IMPORTANT;
            } else {
                var offset = 1;
                System.out.println("RUN");
                var runningResult = character.characterStatusAfterChangeCoordinate(mapModel,  character.runningAway(mapModel, offset));
                while (runningResult.equals(ActionResult.MEET_ENEMY)) {
                    runningResult = character.characterStatusAfterChangeCoordinate(mapModel, character.runningAway(mapModel, ++offset));
                }
                result = runningResult;
            }
        }
        return result;
    }

    private void clearScreen() {
        levelMapView.clean();
    }
}
