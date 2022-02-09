package swingy.utils.button.console;

import swingy.model.character.hero.Hero;
import swingy.model.map.MapModel;
import swingy.utils.ActionResult;
import swingy.utils.exceptions.BreakGameFromKeyboardException;

import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;

public abstract class ButtonPressHandler {

    private final BufferedReader bufferedReader;

    protected ButtonPressHandler(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    public ActionResult handleButtonClick(Hero character, MapModel mapModel) throws IOException, BreakGameFromKeyboardException {
        int buttonCode = bufferedReader.read();

        System.out.println("BUTTON CODE IS " + buttonCode);
        if (buttonCode == 27) {
            throw new BreakGameFromKeyboardException("you press esc - that means stop of all program, goodbye");
        }

//        ActionResult actionResult = ActionResult.RETRY_ENTRY;
//        while (actionResult.equals(ActionResult.RETRY_ENTRY)) {
            return handleExtendedButtonsClick(buttonCode, character, mapModel);
//        }
//        return actionResult;
    }

    public ActionResult handleExtendedButtonsClick(int buttonCode, Hero character, MapModel mapModel) {
        return null;
    }
}
