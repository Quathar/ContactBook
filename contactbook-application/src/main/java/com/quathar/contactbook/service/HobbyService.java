package com.quathar.contactbook.service;

import com.quathar.contactbook.data.entity.Hobby;

import java.util.List;

/**
 * <h1>Hobby Service</h1>
 *
 * @since 2023-05-30
 * @version 1.0
 * @author Q
 */
public interface HobbyService extends GeneralService<Hobby, Long> {

    /**
     * Retrieves all entities that match the specified contactType and name.
     *
     * @param name the name to search
     * @return a list of entities that match the specified parameters
     */
    List<Hobby> getAllByParams(String name);

}
