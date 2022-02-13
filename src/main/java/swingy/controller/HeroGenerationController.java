package swingy.controller;

import swingy.model.character.hero.Hero;
import swingy.utils.ViewMode;
import swingy.view.HeroGenerationView;
import swingy.view.console.HeroGenerationConsoleView;

import static swingy.Game.mainConfig;

public class HeroGenerationController {
    HeroGenerationView heroGenerationView;

    public HeroGenerationController() {
        ViewMode viewMode = mainConfig.getViewMode();
        if (viewMode.equals(ViewMode.CONSOLE)) {
            heroGenerationView = new HeroGenerationConsoleView();
        }
    }

    public Hero heroGenerate() throws Exception {
        heroGenerationView.welcomePage();
        var hero = heroGenerationView.createHero();
        heroGenerationView.clean();
        hero.setMaxLevel(mainConfig.getMaxLevel());
        return hero;
    }
}
