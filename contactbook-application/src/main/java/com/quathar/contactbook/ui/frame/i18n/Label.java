package com.quathar.contactbook.ui.frame.i18n;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <h1>Label</h1>
 *
 * @since 2023-06-06
 * @version 1.0
 * @author Q
 */
@AllArgsConstructor
@Getter
public enum Label {

    MAIN_FRAME_TITLE(Message.MAIN_FRAME_TITLE.getText()),
    ADD_FRAME_TITLE (Message.ADD_FRAME_TITLE.getText()),
    INFO_FRAME_TITLE(Message.INFO_FRAME_TITLE.getText()),
    CONTACT_BROWSER_DEFAULT(Message.CONTACT_BROWSER_DEFAULT.getText()),
    HOBBIES_BROWSER_DEFAULT(Message.HOBBIES_BROWSER_DEFAULT.getText()),
    HOBBIES_INPUT_DEFAULT  (Message.HOBBIES_INPUT_DEFAULT.getText()),
    BTN_REMOVE (Message.BTN_REMOVE.getText()),
    BTN_CONSULT(Message.BTN_CONSULT.getText()),
    BTN_ADD    (Message.BTN_ADD.getText()),
    BTN_RETURN (Message.BTN_RETURN.getText()),
    BTN_SAVE   (Message.BTN_SAVE.getText());

    // <<-FIELDS->>
    private final String text;

}