package swingy.model.DAO;



import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class DAOFactory {
    static {
        heroDAO = new HeroPostgresSQLDAO();
        entityManager = Persistence.createEntityManagerFactory("SwingyCharacterPersistence").createEntityManager();
    }
    private static HeroPostgresSQLDAO heroDAO;
    private static EntityManager entityManager;
    public static HeroPostgresSQLDAO getHeroDAO() {
        return heroDAO;
    }

    public static EntityManager getEntityManager() {
        return entityManager;
    }
}
