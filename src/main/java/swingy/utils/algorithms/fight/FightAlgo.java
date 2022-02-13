package swingy.utils.algorithms.fight;

import swingy.model.character.Artifact;
import swingy.model.character.Character;
import swingy.model.character.hero.Hero;
import swingy.utils.algorithms.random.Randomizers;
import swingy.utils.exceptions.LooseGameException;

public class FightAlgo {
    public static boolean fight(Hero hero, Character enemy) throws LooseGameException {
        var heroArtifact = hero.getArtifact();
        var enemyArtifact = enemy.getArtifact();
        if (heroArtifact == null)
             hero.setArtifact(new Artifact(1, true));
        if (enemyArtifact == null)
            enemy.setArtifact(new Artifact(1, true));
        int enemylowsHp;
        int heroLowsHp;
        hero.setHp(hero.getArtifact().getHpBonus() + hero.getHp());
        while (hero.getHp() > 0 || enemy.getHp() > 0) {
            enemylowsHp = enemy.getDefenceStrength() + enemy.getArtifact().getDefenceBonus() * Randomizers.isEnemyUseArtifact() - hero.getAttackStrength() - hero.getArtifact().getAttackBonus();
            if (enemylowsHp < 0) {
                enemy.setHp(enemy.getHp() + enemylowsHp);
            } else {
                enemy.setHp(enemy.getHp() - enemylowsHp);
            }
           heroLowsHp = hero.getDefenceStrength() + hero.getArtifact().getDefenceBonus() - enemy.getAttackStrength() - enemy.getArtifact().getAttackBonus() * Randomizers.isEnemyUseArtifact();
            if (enemy.getHp() < 0) {
                break;
            }
            hero.setHp(hero.getHp() + heroLowsHp);
        }

        if (hero.getArtifact().isEmpty()) {
            hero.setArtifact(null);
        }
        if (enemy.getArtifact().isEmpty()) {
            enemy.setArtifact(null);
        }

        if (hero.getHp() < 0) {
            throw new LooseGameException("you loose battle, game ends");
        }

        hero.setCurExperience(hero.getCurExperience() + enemy.getExpForWin());
        if (hero.getCurExperience() >= hero.getExpCupForLevel()) {
            hero.upLevel();
            return true;
        }
        return false;

    }
}
