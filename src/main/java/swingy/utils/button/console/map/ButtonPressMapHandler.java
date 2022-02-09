package swingy.utils.button.console.map;

import swingy.model.character.Coordinate;
import swingy.model.character.hero.Hero;
import swingy.model.map.MapModel;
import swingy.utils.ActionResult;
import swingy.utils.button.console.ButtonPressHandler;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;

public class ButtonPressMapHandler extends ButtonPressHandler {

    public ButtonPressMapHandler(BufferedReader bufferedReader) {
        super(bufferedReader);
    }

    @Override
    public ActionResult handleExtendedButtonsClick(int buttonCode, Hero character, MapModel mapModel) {
        mapModel.removeCharacterFromMap(character.getCoordinate());

        if (buttonCode == KeyEvent.VK_A || buttonCode == KeyEvent.VK_LEFT) {
            character.setCoordinate(new Coordinate(character.getCoordinate().getX() - 1, character.getCoordinate().getY()));
        } else if (buttonCode == KeyEvent.VK_W || buttonCode == KeyEvent.VK_UP) {
            character.setCoordinate(new Coordinate(character.getCoordinate().getX(), character.getCoordinate().getY() - 1));
        } else if (buttonCode == KeyEvent.VK_D || buttonCode == KeyEvent.VK_RIGHT) {
            character.setCoordinate(new Coordinate(character.getCoordinate().getX() + 1, character.getCoordinate().getY()));
        } else if (buttonCode == KeyEvent.VK_S || buttonCode == KeyEvent.VK_DOWN) {
            character.setCoordinate(new Coordinate(character.getCoordinate().getX(), character.getCoordinate().getY() + 1));
        } else if (buttonCode == KeyEvent.VK_R) {
            return ActionResult.TRY_TO_RUN;
        } else if (buttonCode == KeyEvent.VK_F) {
            return ActionResult.BATTLE_START;
        } else {
            return (ActionResult.RETRY_ENTRY);
        }

        return character.characterStatusAfterChangeCoordinate(mapModel, character.getCoordinate());
    }
}
