package swingy.view.swing;

import swingy.model.character.Artifact;
import swingy.model.character.Coordinate;
import swingy.model.character.hero.Hero;
import swingy.model.map.MapModel;
import swingy.utils.ActionResult;
import swingy.utils.Button;
import swingy.utils.exceptions.BreakGameFromKeyboardException;
import swingy.utils.map.MapObjectType;
import swingy.view.ButtonHandler;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class SwingyButtonPressHandler extends JFrame implements ButtonHandler {

    public SwingyButtonPressHandler() throws HeadlessException {
        this.setBounds(100, 100,  200, 260);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public ActionResult choiceRunOrFight(Hero character, MapModel mapModel) throws IOException, BreakGameFromKeyboardException {
        if (JOptionPane.showConfirmDialog(this,
                "Do you want to fight?",
                "Choice",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
            return ActionResult.BATTLE_START;
        else
            return ActionResult.TRY_TO_RUN;
    }

    @Override
    public ActionResult handleMapMoveClick(Hero hero, MapModel mapModel, Button button) throws IOException, BreakGameFromKeyboardException {
        if (mapModel.getCharacter(hero.getCoordinate()).getMapObjectType().equals(MapObjectType.HERO)) {
            mapModel.removeCharacterFromMap(hero.getCoordinate());
        }
        switch (button.getCode()) {
            case 65 -> hero.setCoordinate(new Coordinate(hero.getCoordinate().getX() - 1, hero.getCoordinate().getY()));
            case 87 -> hero.setCoordinate(new Coordinate(hero.getCoordinate().getX(), hero.getCoordinate().getY() - 1));
            case 68 -> hero.setCoordinate(new Coordinate(hero.getCoordinate().getX() + 1, hero.getCoordinate().getY()));
            case 83 -> hero.setCoordinate(new Coordinate(hero.getCoordinate().getX(), hero.getCoordinate().getY() + 1));
        }

        return hero.characterStatusAfterChangeCoordinate(mapModel, hero.getCoordinate());
    }

    @Override
    public ActionResult choiceTakeArtifactOrNot(Hero character, Artifact artifact) throws IOException, BreakGameFromKeyboardException {
        boolean result = JOptionPane.showConfirmDialog(this,
                "Take artifact " + artifact.toString() +")?",
                "Choice",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
        return result ? ActionResult.PICK_UP_ARTIFACT : ActionResult.NOTHING_IMPORTANT;
    }
}
