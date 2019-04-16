package pl.sda.store.logic;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import pl.sda.store.model.IBaseEntity;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EntityDao {

    public void save(IBaseEntity objectToSave) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            session.saveOrUpdate(objectToSave);

            transaction.commit();
        } catch (Exception sqle) {// dzięki try - with - resources nie musimy robić 'close' na sesji
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    public <T extends IBaseEntity> List<T> getListOfAll(Class<T> classType) {
        List<T> list = new ArrayList<>();

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();

            // zapytanie na podstawie criteria buildera o CriteriaQuery
            CriteriaQuery<T> query = builder.createQuery(classType);

            // z obiektu root możemy pobrać wartości kolumn.
            // tworzymy go żeby mówić w jakiej tabeli szukamy
            Root<T> tableRoot = query.from(classType);

            // wykonuję zapytanie(query) w tabeli (tableRoot)
            query.select(tableRoot);

            return session.createQuery(query).getResultList();
        } catch (Exception sqle) {// dzięki try - with - resources nie musimy robić 'close' na sesji
            System.err.println("Error: " + sqle.getMessage());
        }

        return list;
    }

    public <T extends IBaseEntity> Optional<T> getById(Class<T> classType, Long searchedId){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {

            return Optional.of(session.get(classType, searchedId));
        } catch (Exception sqle) {// dzięki try - with - resources nie musimy robić 'close' na sesji
            System.err.println("Error: " + sqle.getMessage());
        }

        return Optional.empty();
    }

    public void delete(IBaseEntity objectToDelete) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            session.delete(objectToDelete);

            transaction.commit();
        } catch (Exception sqle) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }



}
