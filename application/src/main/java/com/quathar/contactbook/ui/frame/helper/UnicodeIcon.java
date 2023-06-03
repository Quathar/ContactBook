package com.quathar.contactbook.ui.frame.helper;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <h1>UnicodeIcon</h1>
 *
 * @since 2022-04-07
 * @version 2.0
 * @author Q
 */
@AllArgsConstructor
@Getter
public enum UnicodeIcon {

    SUN("\uE28C"),
    MOON("\uE284"),
    CONTACTS("\uE125"),
    HOBBIES("\uE2B1"),
    SETTINGS("\uE115"),
    MAGNIFYING_GLASS("\uE000"),
    STAR("\uE113"),
    ADD("\uE109"),
    DELETE("\uE108"),
    GAME_CONTROL("\uE2D2"),
    KEYBOARD("\uE2F3"),
    FORMAT("\uE283");

    // <<-FIELDS->>
    private final String code;

}
