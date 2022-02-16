package swingy.model.DAO.entities;

import lombok.Data;
import lombok.NonNull;
import swingy.model.character.hero.HeroClass;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "character")
@Data
public class Character {
    @Id
    @Column(name = "character_id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "character_name")
    private String name;

    @Column(name = "character_level")
    private short level;

    @Column(name = "current_experience")
    private int curExperience;

    @Column(name = "hp")
    private int hp;

    @Column(name = "attack_strength")
    private int attackStrength;

    @Column(name = "defence_strength")
    private int defenceStrength;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER, mappedBy = "hero")
    private List<Artifact> artifacts;

    @Enumerated
    @Column(name = "hero_class")
    private HeroClass heroClass;
}
