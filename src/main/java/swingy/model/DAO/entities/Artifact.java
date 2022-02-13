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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated
    @Column(name = "artifact_type")
    private ArtifactType artifactType;

    @Column(name = "bonus")
    private int bonus;

    @ManyToOne
    @JoinColumn(name = "character", referencedColumnName = "character_id")
    private Character hero;
}
