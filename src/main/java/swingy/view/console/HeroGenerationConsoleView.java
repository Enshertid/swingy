package swingy.view.console;

import swingy.model.character.hero.Hero;
import swingy.model.character.hero.classes.Mage;
import swingy.model.character.hero.classes.Thief;
import swingy.model.character.hero.classes.Warrior;
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
        System.out.println("nice to see you, " + name);
        Thread.sleep(1000);
        System.out.println("adventurer, it's time to define class!");
        Thread.sleep(1000);
        System.out.println("you can choose from 3 proposed classes:");
        System.out.println("1 - MAGE, 2 - WARRIOR, 3 - ARCHER");
        Thread.sleep(1000);
        System.out.println("enter number of chosen class:");

        int classNumber;

        while (true) {
            classNumber = 2;

            if (classNumber == 1) {
                return new Mage(name);
            } else if (classNumber == 2) {
                return new Warrior(name);
            } else if (classNumber == 3) {
                return new Thief(name);
            }
            System.out.println("invalid input, define class!");
        }
    }
}
