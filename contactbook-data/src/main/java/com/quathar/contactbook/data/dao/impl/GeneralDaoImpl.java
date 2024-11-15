package com.quathar.contactbook.data.dao.impl;

import com.quathar.contactbook.data.dao.GeneralDao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import jakarta.persistence.RollbackException;

import java.util.List;
import java.util.Optional;

/**
 * <h1>General DAO Implementation</h1>
 *
 * @param <T> The entity type handled by this DAO.
 * @param <ID> The type of the entity's identifier.
 *
 * @see GeneralDao
 * @since 2
 * @version 1.0
 * @author Q
 */
public abstract class GeneralDaoImpl<T, ID> implements GeneralDao<T, ID> {

    protected final SessionFactory _sessionFactory;
    private final Class<T> _entityClass;

    public GeneralDaoImpl(SessionFactory sessionFactory, Class<T> entityClass) {
        _sessionFactory = sessionFactory;
        _entityClass = entityClass;
    }

    @Override
    public List<T> findAll() {
        try (Session session = _sessionFactory.openSession()) {
            String hql = "FROM ".concat(_entityClass.getSimpleName());
            Query<T> query = session.createQuery(hql, _entityClass);
            return query.list();
        }
    }

    @Override
    public Optional<T> findById(ID id) {
        try (Session session = _sessionFactory.openSession()) {
            T entity = session.get(_entityClass, id);
            return Optional.of(entity);
        }
    }

    @Override
    public T save(T entityToSave) {
        Transaction transaction = null;
        try (Session session = _sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            T savedEntity = session.merge(entityToSave);
            transaction.commit();
            return savedEntity;
        } catch (IllegalStateException | RollbackException ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            return null;
        }
    }

    @Override
    public void deleteAll() {
        Transaction transaction = null;
        try (Session session = _sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            String hql = "DELETE FROM ".concat(_entityClass.getSimpleName());
            Query<T> query = session.createQuery(hql, _entityClass);
            query.executeUpdate();
            transaction.commit();
        } catch (IllegalStateException | RollbackException ex) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void deleteById(ID id) {
        Transaction transaction = null;
        try (Session session = _sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            T entity = session.get(_entityClass, id);
            if (entity != null) {
                session.remove(entity);
            }
            transaction.commit();
        } catch (IllegalStateException | RollbackException ex) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public boolean existsById(ID id) {
        try (Session session = _sessionFactory.openSession()) {
            return session.get(_entityClass, id) != null;
        }
    }

}
