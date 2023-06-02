package com.quathar.contactbook.data.dao.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.quathar.contactbook.data.entity.Contact;
import com.quathar.contactbook.data.dao.ContactDao;
import com.quathar.contactbook.data.enumerator.ContactType;
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
@Singleton
public class ContactDaoImpl implements ContactDao {

    // <<-FIELDS->>
    private final SessionFactory _sessionFactory;

    // <<-CONSTRUCTOR->>
    @Inject
    public ContactDaoImpl(Configuration configuration) {
        _sessionFactory = configuration.buildSessionFactory();
    }

    // <<-METHODS->>
    @Override
    public List<Contact> findAll() {
        try (Session session = _sessionFactory.openSession()) {
            Query<Contact> query = session.createQuery("FROM Contact", Contact.class);
            return query.list();
        }
    }

    @Override
    public Optional<Contact> findById(Long id) {
        try (Session session = _sessionFactory.openSession()) {
            return Optional.of(session.get(Contact.class, id));
        }
    }

    @Override
    public List<Contact> findByParams(ContactType type, String name) {
        try (Session session = _sessionFactory.openSession()) {
            // COALESCE function is to check if the param is null
            // if the param is null the param won't be taken into account
            String hql = "FROM Contact c WHERE " +
                         "(COALESCE(:type, '') = '' OR c.type = :type) AND " +
                         "(COALESCE(:name, '') = '' OR c.name LIKE CONCAT('%', :name, '%'))";
            Query<Contact> query = session.createQuery(hql, Contact.class);
            query.setParameter("type", type);
            query.setParameter("name", name);

            return query.list();
        }
    }

    @Override
    public Contact save(Contact contact) {
        Transaction transaction = null;
        try (Session session = _sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(contact);
            transaction.commit();
        } catch (IllegalStateException | RollbackException ex) {
            if (transaction != null)
                transaction.rollback();
            return null;
        }
        return contact;
    }

    @Override
    public void deleteAll() {
        Transaction transaction = null;
        try (Session session = _sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Query<Contact> query = session.createQuery("DELETE FROM Contact", Contact.class);
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
            Contact contact = session.get(Contact.class, id);
            if (contact != null)
                session.remove(contact);
            transaction.commit();
        } catch (IllegalStateException | RollbackException ex) {
            if (transaction != null)
                transaction.rollback();
        }
    }

}
