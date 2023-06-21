package com.quathar.contactbook.ui.frame.i18n;

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
    CONTACT_DEFAULT_TEXT (Message.CONTACT_BROWSER_DEFAULT.getText()),
    HOBBY_DEFAULT_TEXT   (Message.HOBBIES_BROWSER_DEFAULT.getText()),
    HOBBY_TF_DEFAULT_TEXT(Message.HOBBIES_INPUT_DEFAULT.getText()),
    NAME      (Message.PLACEHOLDER_NAME.getText()),
    SURNAMES  (Message.PLACEHOLDER_SURNAMES.getText()),
    ADDRESS   (Message.PLACEHOLDER_ADDRESS.getText()),
    NOTES     (Message.PLACEHOLDER_NOTES.getText()),
    TELEPHONES(Message.PLACEHOLDER_TELEPHONE.getText()),
    MAILS     (Message.PLACEHOLDER_MAIL.getText());

    // <<-FIELDS->>
    private final String text;

}