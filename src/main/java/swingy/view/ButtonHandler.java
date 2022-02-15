package swingy.view;

import swingy.model.character.Artifact;
import swingy.model.character.hero.Hero;
import swingy.model.map.MapModel;
import swingy.utils.ActionResult;
import swingy.utils.Button;
import swingy.utils.exceptions.BreakGameFromKeyboardException;

import java.io.IOException;

public interface ButtonHandler {
    ActionResult choiceRunOrFight(Hero character, MapModel mapModel) throws IOException, BreakGameFromKeyboardException;
    ActionResult handleMapMoveClick(Hero character, MapModel mapModel, Button button) throws IOException, BreakGameFromKeyboardException;
    ActionResult choiceTakeArtifactOrNot(Hero character, Artifact artifact) throws IOException, BreakGameFromKeyboardException;
}
