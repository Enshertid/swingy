package swingy.model.DAO.entities;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "character")
@Data
public class Character {
    @Id
    @Column(name = "character_id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @SequenceGenerator(name = "characterSequence", sequenceName = "character", allocationSize = 1, initialValue = 1)
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
}
