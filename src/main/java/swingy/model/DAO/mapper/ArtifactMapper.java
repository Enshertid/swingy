package swingy.model.DAO.mapper;

import swingy.model.character.Artifact;

public class ArtifactMapper {
    public static swingy.model.DAO.entities.Artifact toEntity(Artifact artifact) {
        var entity = new swingy.model.DAO.entities.Artifact();
        entity.setArtifactType(artifact.getArtifactType());
        entity.setBonus(artifact.getBonus());
        entity.setId(artifact.getId());
        return entity;
    }

    public static Artifact toModel(swingy.model.DAO.entities.Artifact artifact) {
        var model = new Artifact(artifact.getArtifactType(), artifact.getBonus(), false);
        model.setId(artifact.getId());
        return model;
    }
}
