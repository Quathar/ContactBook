package com.quathar.contactbook.data.repository.impl;

import com.quathar.contactbook.data.entity.Contact;
import com.quathar.contactbook.data.repository.ContactRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class ContactRepositoryImpl implements ContactRepository {

    // <<-FIELDS->>
    private final SessionFactory _sessionFactory;

    // <<-CONSTRUCTOR->>
    public ContactRepositoryImpl() {
        Configuration configuration = new Configuration();
        _sessionFactory = configuration.buildSessionFactory();
    }

    // <<-METHODS->>
    @Override
    public List<Contact> findAll() {
        Session session = _sessionFactory.openSession();
        Query<Contact> query = session.createQuery("FROM Contact", Contact.class);
        List<Contact> contacts = query.list();
        session.close();
        return contacts;
    }

    @Override
    public Contact findById(Long id) {
        Session session = _sessionFactory.openSession();
        Contact contact = session.get(Contact.class, id);
        session.close();
        return contact;
    }

    @Override
    public List<Contact> findByParams(String type, String name) {
        Session session = _sessionFactory.openSession();

        String hql = "SELECT c FROM Contact c WHERE " +
                "(:type IS NULL OR :type = '' OR c.type = :type) AND " +
                "(:name IS NULL OR :name = '' OR c.name LIKE CONCAT('%', :name, '%'))";

        Query<Contact> query = session.createQuery(hql, Contact.class);

        query.setParameter("type", type);
        query.setParameter("name", name);

        List<Contact> contacts = query.list();
        session.close();
        return contacts;
    }

    @Override
    public Contact save(Contact entity) {
        Session session = _sessionFactory.openSession();
        session.beginTransaction();
        session.save(entity);
        session.getTransaction()
               .commit();
        session.close();
        return entity;
    }

    @Override
    public void deleteAll() {
        Session session = _sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query<Contact> query = session.createQuery("DELETE FROM Contact", Contact.class);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteById(Long id) {
        Session session = _sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Contact contact = session.get(Contact.class, id);
            if (contact != null) {
                session.delete(contact);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }

}
