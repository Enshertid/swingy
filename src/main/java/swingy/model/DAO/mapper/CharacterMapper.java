package swingy.model.DAO.mapper;

import swingy.model.DAO.entities.Artifact;
import swingy.model.DAO.entities.Character;
import swingy.model.character.ArtifactType;
import swingy.model.character.hero.Hero;

import java.util.ArrayList;

public class CharacterMapper {
    public static Hero toModel(Character character) {
        Hero hero = new Hero(character.getName());
        hero.setId(character.getId());
        hero.setMaxHp(character.getHp());
        hero.setHp(character.getHp());
        hero.setAttackStrength(character.getAttackStrength());
        hero.setLevel(character.getLevel());
        hero.setCurExperience(character.getCurExperience());
        hero.setDefenceStrength(character.getDefenceStrength());
        character.getArtifacts().forEach(artifact -> hero.setArtifact(ArtifactMapper.toModel(artifact)));
        return hero;
    }
    public static Character toEntity(Hero hero) {
        var character = new Character();
        var artifacts = new ArrayList<Artifact>();

        if (hero.getArmor() != null) {
            var armor = ArtifactMapper.toEntity(hero.getArmor());
            armor.setHero(character);
            artifacts.add(armor);
        }

        if (hero.getHelm() != null) {
            var helm = ArtifactMapper.toEntity(hero.getHelm());
            helm.setHero(character);
            artifacts.add(helm);
        }

        if (hero.getSword() != null) {
            var sword = ArtifactMapper.toEntity(hero.getSword());
            sword.setHero(character);
            artifacts.add(sword);
        }

        if (!artifacts.isEmpty())  {
            character.setArtifacts(artifacts);
        }

        character.setAttackStrength(hero.getAttackStrength());
        character.setCurExperience(hero.getCurExperience());
        character.setDefenceStrength(hero.getDefenceStrength());
        character.setLevel(hero.getLevel());
        character.setName(hero.getName());
        character.setHp(hero.getHp());
        if (hero.getId() != null) {
            character.setId(hero.getId());
        }
        return character;
    }

}
