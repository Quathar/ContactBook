package com.quathar.contactbook.data.repository.impl;

import com.quathar.contactbook.data.entity.Hobby;
import com.quathar.contactbook.data.repository.HobbyRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class HobbyRepositoryImpl implements HobbyRepository {

    // <<-FIELDS->>
    private final SessionFactory _sessionFactory;

    // <<-CONSTRUCTOR->>
    public HobbyRepositoryImpl() {
        Configuration configuration = new Configuration();
        _sessionFactory = configuration.buildSessionFactory();
    }

    // <<-METHODS->>
    @Override
    public List<Hobby> findAll() {
        Session session = _sessionFactory.openSession();
        Query<Hobby> query = session.createQuery("FROM Hobby", Hobby.class);
        List<Hobby> hobbies = query.list();
        session.close();
        return hobbies;
    }

    @Override
    public Hobby findById(Long id) {
        Session session = _sessionFactory.openSession();
        Hobby hobby = session.get(Hobby.class, id);
        session.close();
        return hobby;
    }

    @Override
    public Hobby save(Hobby entity) {
        Session session = _sessionFactory.openSession();
        session.beginTransaction();
        session.save(entity);
        session.getTransaction()
               .commit();
        session.close();

        // UPDATE
//        SessionFactory sessionFactory = . . .
//        Session session = sessionFactory.openSession();
//        session.beginTransaction();
//        session.update(updatedEntity);
//        session.getTransaction().commit();
//        session.close();
        return entity;
    }

    @Override
    public void deleteAll() {
        System.err.println("Borrado");
    }

    @Override
    public void deleteById(Long id) {
        System.err.println("Borrado por id");

    }

}
