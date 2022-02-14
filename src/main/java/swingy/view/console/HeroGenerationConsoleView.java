package swingy.view.console;

import swingy.model.DAO.DAOFactory;
import swingy.model.DAO.entities.Character;
import swingy.model.DAO.mapper.CharacterMapper;
import swingy.model.character.hero.Hero;
import swingy.utils.exceptions.BreakGameFromKeyboardException;
import swingy.view.HeroGenerationView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

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
        System.out.println("you can move character by W|A|S|D buttons");
        System.out.println("you can break game if you enter 'exit'");
        System.out.println("after enter 'exit' you progress will be saved in database");
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

    @Override
    public boolean isHeroGeneratedOrTakenFromDb() throws IOException, BreakGameFromKeyboardException {
        System.out.println("do you want to create new hero, or take already exist progress?");
        System.out.println("enter 'new' if you want to create hero");
        System.out.println("enter 'old' if you want to take already exists hero");
        String result;
        while (!isHeroGenerationAnswer(result = bufferedReader.readLine())) {
            System.out.println("enter 'new' or 'old");
        }
        if (result.equals("new")) {
            return true;
        } else if (result.equals("old")) {
            return false;
        } else {
            throw new BreakGameFromKeyboardException("you enter 'exit' it means break the game, goodbye!");
        }
    }

    private boolean isHeroGenerationAnswer(String s) {
        return s.equals("new") || s.equals("old") || s.equals("exit");
    }

    @Override
    public Hero choiceOldHero() throws InterruptedException, IOException {
        List<Character> charactersFromDb = DAOFactory.getHeroDAO().getAll();
        List<Hero> heroes = charactersFromDb.stream().map(CharacterMapper::toModel).toList();
        System.out.println("you can restore next adventurers:");
        int i = 1;
        for (Hero hero : heroes) {
            System.out.println("#" + i + " - ");
            System.out.println(hero.toString());
            i++;
        }
        System.out.println("choose wisely..");
        Thread.sleep(1000);
        System.out.println("enter number of hero");
        String result;
        int number;
        boolean flag = false;
        do {
            if (flag) {
                System.out.println("number must be less or equal " + i);
            }
            if (!flag) {
                flag = true;
            }
            while (!isHeroNumberAnswer(result = bufferedReader.readLine())) {
                System.out.println("enter number of hero...");
            }
            number = Integer.parseInt(result);
        } while (number > i);
        return heroes.get(i - 1);
    }

    private boolean isHeroNumberAnswer(String s) {
        String regex = "\\d+";
        return s.matches(regex) || s.equals("exit");
    }
}
