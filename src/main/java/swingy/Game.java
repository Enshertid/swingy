package swingy;

import swingy.controller.HeroGenerationController;
import swingy.controller.LevelMapController;
import swingy.model.character.Coordinate;
import swingy.model.character.hero.Hero;
import swingy.utils.GameConfig;
import swingy.utils.ViewMode;
import swingy.utils.exceptions.BreakGameFromKeyboardException;
import swingy.utils.exceptions.LooseGameException;

public class Game {
    public static GameConfig mainConfig = new GameConfig();

    public static void main(String[] args) {
        validateStartInput(args);

        Hero character = generateCharacter();
        LevelMapController levelMapController = new LevelMapController();

        launchGameAndHandleResult(character, levelMapController);
    }

    private static void launchGameAndHandleResult(Hero character, LevelMapController levelMapController) {
            try {
                levelMapController.gameCycle(character);
            } catch (BreakGameFromKeyboardException exception) {
                System.out.println("you break the game, goodbye!");
                System.exit(0);
                //method for saving data in storage
            } catch (LooseGameException exception) {
                System.out.println("you loose game, goodbye");
                //method for cleaning data;
            } catch (Exception exception) {
                System.out.println("unexpected exception:");
                exception.printStackTrace();
                System.exit(1);
            }
            //method for won game
        }

    private static Hero generateCharacter() {
        try {
           return new HeroGenerationController().heroGenerate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        return null;
    }

    private static void validateStartInput(String[] args) {
        if (args.length == 1 && args[0].equals("console")) {
            mainConfig.setViewMode(ViewMode.CONSOLE);
        } else if (args.length == 1 && args[0].equals("gui")) {
            mainConfig.setViewMode(ViewMode.GUI);
        } else {
            System.out.println("usage:");
            System.out.println("[launch jar] gui");
            System.out.println("[launch jar] console");
            System.exit(1);
        }
    }
}
