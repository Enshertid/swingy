package swingy.controller;

import swingy.model.DAO.DAOFactory;
import swingy.model.DAO.entities.Character;
import swingy.model.DAO.mapper.CharacterMapper;
import swingy.model.character.hero.Hero;
import swingy.utils.ViewMode;
import swingy.view.HeroGenerationView;
import swingy.view.console.HeroGenerationConsoleView;
import swingy.view.swing.HeroGenerationSwingyView;

import java.util.List;

import static swingy.Game.mainConfig;

public class HeroGenerationController {
    HeroGenerationView heroGenerationView;

    public HeroGenerationController() {
//        ViewMode viewMode = mainConfig.getViewMode();
        ViewMode viewMode = ViewMode.GUI;
        if (viewMode.equals(ViewMode.CONSOLE)) {
            heroGenerationView = new HeroGenerationConsoleView();
        } else {
            heroGenerationView = new HeroGenerationSwingyView();
        }
    }

    public Hero heroGenerate() throws Exception {
        heroGenerationView.welcomePage();
        Hero hero;
        if (heroGenerationView.isHeroGeneratedOrTakenFromDb()) {
            hero = heroGenerationView.createHero();
        } else {
            List<Character> charactersFromDb = DAOFactory.getHeroDAO().getAll();
            List<Hero> heroes = charactersFromDb.stream().map(CharacterMapper::toModel).toList();
            hero = heroGenerationView.choiceOldHero(heroes);
        }
        heroGenerationView.clean();
        hero.setMaxLevel(mainConfig.getMaxLevel());
        return hero;
    }
}
