package swingy.model.DAO.mapper;

import swingy.model.DAO.entities.Artifact;
import swingy.model.DAO.entities.Character;
import swingy.model.character.ArtifactType;
import swingy.model.character.hero.Hero;

import java.util.ArrayList;

public class CharacterMapper {
    public static Hero toModel(Character character) {
        return null;
    }
    public static Character toEntity(Hero hero) {
        var character = new Character();
        var artifacts = new ArrayList<Artifact>();

        if (hero.getArmor() != null) {
            var armor = new Artifact();
            armor.setArtifactType(ArtifactType.ARMOR);
            armor.setHero(character);
            armor.setBonus(hero.getArmor().getBonus());
            artifacts.add(armor);
        }

        if (hero.getHelm() != null) {
            var helm = new Artifact();
            helm.setArtifactType(ArtifactType.HELM);
            helm.setHero(character);
            helm.setBonus(hero.getHelm().getBonus());
            artifacts.add(helm);
        }

        if (hero.getSword() != null) {
            var sword = new Artifact();
            sword.setHero(character);
            sword.setBonus(hero.getSword().getBonus());
            sword.setArtifactType(ArtifactType.SWORD);
            artifacts.add(new Artifact());
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
        character.setId(hero.getId());
        return character;
    }
}
