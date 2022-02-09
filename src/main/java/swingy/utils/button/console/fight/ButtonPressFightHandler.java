package swingy.utils.button.console.fight;

import swingy.model.character.hero.Hero;
import swingy.model.map.MapModel;
import swingy.utils.ActionResult;
import swingy.utils.button.console.ButtonPressHandler;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;

public class ButtonPressFightHandler extends ButtonPressHandler {

    public ButtonPressFightHandler(BufferedReader bufferedReader) {
        super(bufferedReader);
    }

    @Override
    public ActionResult handleExtendedButtonsClick(int buttonCode, Hero character, MapModel mapModel) {
        return null;
    }
}
