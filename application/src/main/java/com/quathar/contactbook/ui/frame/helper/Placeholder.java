package com.quathar.contactbook.ui.frame.helper;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <h1>Placeholder</h1>
 *
 * @since 2023-06-04
 * @version 1.0
 * @author Q
 */
@AllArgsConstructor
@Getter
public enum Placeholder {

    BLANK(""),
    NAME("Name..."),
    SURNAMES("Surnames..."),
    ADDRESS("Address..."),
    NOTES("Notes..."),
    TELEPHONES("Telephone..."),
    MAILS("Mail...");

    // <<-FIELDS->>
    private final String text;

}
