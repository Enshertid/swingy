package swingy.view;

import swingy.model.character.hero.Hero;
import swingy.utils.exceptions.BreakGameFromKeyboardException;

import java.io.IOException;

public interface HeroGenerationView extends View {
    void welcomePage() throws IOException, BreakGameFromKeyboardException;
    Hero createHero() throws Exception;
}
