package com.quathar.contactbook.data.dao.impl;

import com.google.inject.Inject;

import com.quathar.contactbook.data.dao.ContactDao;
import com.quathar.contactbook.data.entity.Contact;
import com.quathar.contactbook.data.enumerator.ContactType;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

/**
 * <h1>Contact DAO Implementation</h1>
 *
 * @see ContactDao
 * @see GeneralDaoImpl
 * @since 2023-05-30
 * @version 1.0
 * @author Q
 */
public class ContactDaoImpl extends GeneralDaoImpl<Contact, Long> implements ContactDao {

    @Inject
    public ContactDaoImpl(Configuration configuration) {
        super(configuration.buildSessionFactory(), Contact.class);
    }

    @Override
    public List<Contact> findByParams(ContactType type, String name) {
        try (Session session = _sessionFactory.openSession()) {
            // COALESCE function is to check if the param is null
            // if the param is null it won't be taken into consideration
            String hql = "FROM Contact c WHERE " +
                         "(COALESCE(:type, '') = '' OR c.type = :type) AND " +
                         "(COALESCE(:name, '') = '' OR c.name LIKE CONCAT('%', :name, '%'))";
            Query<Contact> query = session.createQuery(hql, Contact.class);
            query.setParameter("type", type);
            query.setParameter("name", name);

            return query.list();
        }
    }

}
