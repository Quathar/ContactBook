package com.quathar.contactbook.data.service;

import com.quathar.contactbook.data.entity.Hobby;

import java.util.List;

/**
 * @since 2023-05-30
 * @version 1.0
 * @author Q
 */
public interface HobbyService extends GeneralService<Hobby, Long> {

    List<Hobby> getAllByParams(String name);

}
