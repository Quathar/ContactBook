package com.quathar.contactbook.data.enumerator;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <h1>Telephone Type</h1>
 *
 * @since 2023-05-30
 * @version 1.0
 * @author Q
 */
@AllArgsConstructor
@Getter
public enum TelephoneType {

    M("M", "Mobile"),
    L("L", "Landline");

    // <<-FIELDS->>
    private final String value;
    private final String text;

}