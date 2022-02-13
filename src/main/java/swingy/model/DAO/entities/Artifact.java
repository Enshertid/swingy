package swingy.model.DAO.entities;

import lombok.Data;
import lombok.Generated;
import swingy.model.character.ArtifactType;

import javax.persistence.*;


@Entity
@Table(name = "artifact")
@Data
public class Artifact {
    @Id
    @Column(name = "artifact_id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "artifactSequence")
    @SequenceGenerator(name = "artifactSequence", sequenceName = "artifact_id", allocationSize = 1, initialValue = 1)
    private int id;

    @Enumerated
    @Column(name = "artifact_type")
    private ArtifactType artifactType;

    @Column(name = "bonus_to_attribute")
    private int bonus;

    @ManyToOne
    @JoinColumn(name = "character", referencedColumnName = "character_id")
    private Character hero;
}
