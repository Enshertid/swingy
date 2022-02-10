package swingy.utils.algorithms;

import swingy.model.character.hero.Hero;
import swingy.model.map.MapModel;
import swingy.utils.ActionResult;

public class RunAlgo {
    public static void run(Hero character, MapModel mapModel) {
        var offset = 1;
        var runningResult = character.characterStatusAfterChangeCoordinate(mapModel,  character.runningAway(mapModel, offset));
        while (runningResult.equals(ActionResult.MEET_ENEMY)) {
            runningResult = character.characterStatusAfterChangeCoordinate(mapModel, character.runningAway(mapModel, ++offset));
        }
    }
}
