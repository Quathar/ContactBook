package com.quathar.contactbook.data.dao.impl;

import com.google.inject.Inject;

import com.quathar.contactbook.data.dao.HobbyDao;
import com.quathar.contactbook.data.entity.Hobby;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

/**
 * <h1>Hobby DAO Implementation</h1>
 *
 * @see HobbyDao
 * @see GeneralDaoImpl
 * @since 2023-05-30
 * @version 1.0
 * @author Q
 */
public class HobbyDaoImpl extends GeneralDaoImpl<Hobby, Long> implements HobbyDao {

    @Inject
    public HobbyDaoImpl(Configuration configuration) {
        super(configuration.buildSessionFactory(), Hobby.class);
    }

    @Override
    public List<Hobby> findByParams(String name) {
        try (Session session = _sessionFactory.openSession()) {
            String hql = "FROM Hobby h WHERE " +
                         "(COALESCE(:name, '') = '' OR h.name LIKE CONCAT('%', :name, '%'))";
            Query<Hobby> query = session.createQuery(hql, Hobby.class);
            query.setParameter("name", name);
            return query.list();
        }
    }

}
