package swingy.utils.algorithms.fight;

import swingy.model.character.Character;
import swingy.model.character.hero.Hero;
import swingy.utils.FightResult;
import swingy.utils.exceptions.LooseGameException;

public class FightAlgo {
    public static FightResult fight(Hero hero, Character enemy) throws LooseGameException {
        hero.setLevel(hero.getLevel() + 1);
        if (hero.getLevel() < 0) {
            throw new LooseGameException("battle lost, your character is dead, gg");
        }
        return FightResult.fight;
    }
}
