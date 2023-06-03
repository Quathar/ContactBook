package com.quathar.contactbook.data.dao.impl;

import com.google.inject.Inject;
import com.quathar.contactbook.data.dao.HobbyDao;
import com.quathar.contactbook.data.entity.Hobby;
import jakarta.persistence.RollbackException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

/**
 * @since 2023-05-30
 * @version 1.0
 * @author Q
 */
public class HobbyDaoImpl implements HobbyDao {

    // <<-FIELDS->>
    private final SessionFactory _sessionFactory;

    // <<-CONSTRUCTOR->>
    @Inject
    public HobbyDaoImpl(Configuration configuration) {
        _sessionFactory = configuration.buildSessionFactory();
    }

    // <<-METHODS->>
    @Override
    public List<Hobby> findAll() {
        try (Session session = _sessionFactory.openSession()) {
            Query<Hobby> query = session.createQuery("FROM Hobby", Hobby.class);
            return query.list();
        }
    }

    @Override
    public Optional<Hobby> findById(Long id) {
        try (Session session = _sessionFactory.openSession()) {
            return Optional.of(session.get(Hobby.class, id));
        }
    }

    @Override
    public List<Hobby> findByParams(String name) {
        try (Session session = _sessionFactory.openSession()) {
            String hql = "SELECT h FROM Hobby h WHERE " +
                         "(:name IS NULL OR :name = '' OR h.name LIKE %:name%)";
            Query<Hobby> query = session.createQuery(hql, Hobby.class);
            query.setParameter("name", name);
            return query.list();
        }
    }

    @Override
    public Hobby save(Hobby hobby) {
        Transaction transaction = null;
        try (Session session = _sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(hobby);
            transaction.commit();
        } catch (IllegalStateException | RollbackException ex) {
            if (transaction != null)
                transaction.rollback();
            return null;
        }
        return hobby;
    }

    @Override
    public void deleteAll() {
        Transaction transaction = null;
        try (Session session = _sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Query<Hobby> query = session.createQuery("DELETE FROM Hobby", Hobby.class);
            query.executeUpdate();
            transaction.commit();
        } catch (IllegalStateException | RollbackException ex) {
            if (transaction != null)
                transaction.rollback();
        }
    }

    @Override
    public void deleteById(Long id) {
        Transaction transaction = null;
        try (Session session = _sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Hobby hobby = session.get(Hobby.class, id);
            if (hobby != null)
                session.remove(hobby);
            transaction.commit();
        } catch (IllegalStateException | RollbackException ex) {
            if (transaction != null)
                transaction.rollback();
        }
    }

}
