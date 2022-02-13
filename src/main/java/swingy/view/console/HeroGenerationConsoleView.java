package swingy.view.console;

import swingy.model.character.hero.Hero;
import swingy.utils.exceptions.BreakGameFromKeyboardException;
import swingy.view.HeroGenerationView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HeroGenerationConsoleView extends ConsoleView implements HeroGenerationView {

    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public void welcomePage() throws IOException, BreakGameFromKeyboardException {
        System.out.println("greetings in swingy!\n" +
                "dear adventurer, if you want to play, press enter...");
        int attempts = 0;
        while (bufferedReader.read() != 10) {
            if (attempts == 10) {
                throw new BreakGameFromKeyboardException("You press wrong key 10 times, wooops");
            }
            System.out.println("i said, press enter, you have " + (10 - attempts) + "attempts");
            attempts++;
        }
    }

    @Override
    public Hero createHero() throws Exception {
        System.out.print("adventurer, enter your name: ");
        var name = bufferedReader.readLine();
        name = name.equals("") ? "adventurer" : name;
        System.out.println("nice to see you, " + name);
        Thread.sleep(1000);

        return new Hero(name);

    }
}
