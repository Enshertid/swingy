package swingy.view;

import swingy.model.character.hero.Hero;
import swingy.utils.exceptions.BreakGameFromKeyboardException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface HeroGenerationView extends View {
    void welcomePage() throws IOException, BreakGameFromKeyboardException;
    Hero createHero() throws Exception;
    boolean isHeroGeneratedOrTakenFromDb() throws IOException, BreakGameFromKeyboardException;
    Hero choiceOldHero(List<Hero> heroes) throws InterruptedException, IOException;
}
