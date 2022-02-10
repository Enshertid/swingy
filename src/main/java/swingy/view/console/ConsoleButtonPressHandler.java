package swingy.view.console;

import swingy.model.character.Artifact;
import swingy.model.character.Coordinate;
import swingy.model.character.hero.Hero;
import swingy.model.map.MapModel;
import swingy.utils.ActionResult;
import swingy.utils.exceptions.BreakGameFromKeyboardException;
import swingy.view.ButtonHandler;

import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;

public class ConsoleButtonPressHandler implements ButtonHandler {

    private final BufferedReader bufferedReader;

    public ConsoleButtonPressHandler(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    @Override
    public ActionResult choiceRunOrFight(Hero character, MapModel mapModel) throws IOException, BreakGameFromKeyboardException {
        String fightOrRun;
        while (!isButtonFight(fightOrRun = bufferedReader.readLine())) {
            System.out.println("press F for fight or R for run");
        }
        if (fightOrRun.equals("F")) {
            return ActionResult.BATTLE_START;
        } else if (fightOrRun.equals("R")) {
            return ActionResult.TRY_TO_RUN;
        } else {
            throw new BreakGameFromKeyboardException("you print 'exit' it's means that you want to break the game");
        }
    }

    @Override
    public ActionResult choiceTakeArtifactOrNot(Hero character, Artifact artifact) throws IOException, BreakGameFromKeyboardException {
        String takeOrNot;
        while (!yesOrNo(takeOrNot = bufferedReader.readLine())) {
            System.out.println("press yes or no for pick artifact");
        }
        if (takeOrNot.equals("yes")) {
            return ActionResult.PICK_UP_ARTIFACT;
        } else if (takeOrNot.equals("no")) {
            return ActionResult.NOTHING_IMPORTANT;
        } else {
            throw new BreakGameFromKeyboardException("you print 'exit' it's means that you want to break the game");
        }
    }


    private boolean yesOrNo(String s) {
        return (s.equals("yes")) || (s.equals("no")) || (s.equals("exit"));
    }

    private boolean isButtonFight(String s) {
        return s.equals("F") || s.equals("R") || isButtonEscape(s);
    }

    @Override
    public ActionResult handleMapMoveClick(Hero character, MapModel mapModel) throws IOException, BreakGameFromKeyboardException {
        String input;

        while (!isButtonMove(input = bufferedReader.readLine())) {
            System.out.println("W for move up, D for move right, S for move down and A for move left and 'exit' for break game all other buttons are ignored while hero is moving");
        }

       if (isButtonEscape(input)) {
           throw new BreakGameFromKeyboardException("you print 'exit' it's means that you want to break the game");
       }

        return handleExtendedButtonsClick(input, character, mapModel);
    }

    private boolean isButtonMove(String s)  {
        return s.equals("A") || s.equals("S") || s.equals("D") || s.equals("W") || isButtonEscape(s);
    }

    private boolean isButtonEscape(String input){
        return input.equals("exit");
    }

    public ActionResult handleExtendedButtonsClick(String key, Hero character, MapModel mapModel) throws BreakGameFromKeyboardException {
        mapModel.removeCharacterFromMap(character.getCoordinate());

        switch (key) {
            case "A" -> character.setCoordinate(new Coordinate(character.getCoordinate().getX() - 1, character.getCoordinate().getY()));
            case "W" -> character.setCoordinate(new Coordinate(character.getCoordinate().getX(), character.getCoordinate().getY() - 1));
            case "D" -> character.setCoordinate(new Coordinate(character.getCoordinate().getX() + 1, character.getCoordinate().getY()));
            case "S" -> character.setCoordinate(new Coordinate(character.getCoordinate().getX(), character.getCoordinate().getY() + 1));
        }

        return character.characterStatusAfterChangeCoordinate(mapModel, character.getCoordinate());
    }
}
