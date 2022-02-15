package swingy.utils.algorithms.fight;

import swingy.model.character.Artifact;
import swingy.model.character.Character;
import swingy.model.character.hero.Hero;
import swingy.utils.algorithms.random.Randomizers;
import swingy.utils.exceptions.LooseGameException;

public class FightAlgo {
    public static boolean fight(Hero hero, Character enemy) throws LooseGameException {
        hero.setEmptyArtifacts();
        enemy.setEmptyArtifacts();

        int enemyLowsHp;
        int heroLowsHp;
        hero.setHp(hero.getHelm().getBonus() + hero.getHp());

        var flag = false;

        if (enemy.getDefenceStrength() == hero.getAttackStrength()) {
            flag = true;
            hero.setAttackStrength(hero.getAttackStrength() + 1);
        }

        while (hero.getHp() > 0 || enemy.getHp() > 0) {
            enemyLowsHp = enemy.getDefenceStrength() + enemy.getArmor().getBonus() * Randomizers.isEnemyUseArtifact() - hero.getAttackStrength() - hero.getSword().getBonus();
            if (enemyLowsHp < 0) {
                enemy.setHp(enemy.getHp() + enemyLowsHp);
            } else {
                enemy.setHp(enemy.getHp() - enemyLowsHp);
            }
           heroLowsHp = hero.getDefenceStrength() + hero.getArmor().getBonus() - enemy.getAttackStrength() - enemy.getSword().getBonus() * Randomizers.isEnemyUseArtifact();
            if (enemy.getHp() < 0) {
                break;
            }
            hero.setHp(hero.getHp() + heroLowsHp);
        }

        hero.resetEmptyArtifacts();
        enemy.resetEmptyArtifacts();

        if (hero.getHp() < 0 ) {
            throw new LooseGameException("you loose battle, game ends");
        }
        if (Randomizers.getRandomDeath() == 5734) {
            throw new LooseGameException("today is not your day, you loose, game over");
        }

        hero.setHp(hero.getMaxHp());

        hero.setCurExperience(hero.getCurExperience() + enemy.getExpForWin());
        if (hero.getCurExperience() >= hero.getExpCupForLevel()) {
            hero.upLevel();
            return true;
        }
        if (flag) {
            hero.setAttackStrength(hero.getAttackStrength() - 1
            );
        }
        return false;

    }
}
