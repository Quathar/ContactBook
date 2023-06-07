package com.quathar.contactbook.data.enumerator;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <h1>Gender</h1>
 *
 * @since 2023-05-30
 * @version 1.0
 * @author Q
 */
@AllArgsConstructor
@Getter
public enum Gender {

    M("M", "Male"),
    F("F", "Female"),
    O("O", "Other");

    // <<-FIELDS->>
    private final String value;
    private final String text;

}
