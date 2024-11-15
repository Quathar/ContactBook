package com.quathar.contactbook.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * <h1>Language Enum</h1>
 *
 * @since 2023-06-02
 * @version 1.0
 * @author Q
 */
@Getter
@RequiredArgsConstructor
public enum LangEnum {

    EN("en"),
    ES("es");

    private final String language;

}
