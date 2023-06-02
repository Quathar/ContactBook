package com.quathar.contactbook.data.dao;

import com.quathar.contactbook.data.entity.Hobby;

import java.util.List;

/**
 * <h1>Hobby DAO (Data Access Object)</h1>
 *
 * @since 2023-05-30
 * @version 1.0
 * @author Q
 */
public interface HobbyDao extends GeneralDao<Hobby, Long> {

    List<Hobby> findByParams(String name);

}
