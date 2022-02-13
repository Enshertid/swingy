package swingy.model.DAO;

import swingy.model.DAO.entities.Character;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class HeroPostgresSQLDAO implements Dao<Character> {

    @Override
    public List<Character> getAll() {
        var query = DAOFactory.getEntityManager().createQuery("select heroes from Character heroes", Character.class);
        return query.getResultList();
    }

    @Override
    public Character getByName(String name) {
        var query = DAOFactory.getEntityManager()
                .createQuery("select hero from Character hero where hero.name = :name", Character.class)
                .setParameter("name", name);
        return query.getSingleResult();
    }

    @Override
    public void save(Character object) {
        DAOFactory.getEntityManager().getTransaction().begin();
        DAOFactory.getEntityManager().persist(object);
        DAOFactory.getEntityManager().getTransaction().commit();

    }

    @Override
    public void update(Character object) {
        EntityManager entityManager = DAOFactory.getEntityManager();
        entityManager.getTransaction().begin();
        Query q = entityManager.createQuery("SELECT character FROM Character character where character.name=:name").setParameter("name", object.getName());
        List<Character> resultList = (List<Character>) q.getResultList();
        if (resultList.size() == 0) {
            save(object);
        } else {
            entityManager.merge(object);
            entityManager.flush();
            entityManager.getTransaction().commit();
        }
    }

    @Override
    public void delete(Character object) {
        DAOFactory.getEntityManager().createQuery("delete from Character c where c.name =: name").setParameter("name", object.getName());
    }
}
