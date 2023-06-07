package com.quathar.contactbook.ui.frame.helper;

import com.quathar.contactbook.config.AppConfiguration;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Properties;

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

    MAIN_FRAME_TITLE(""),
    ADD_FRAME_TITLE (""),
    INFO_FRAME_TITLE(""),
    CONTACT_BROWSER_DEFAULT(""),
    HOBBIES_BROWSER_DEFAULT(""),
    HOBBIES_INPUT_DEFAULT(""),
    BTN_REMOVE (""),
    BTN_CONSULT(""),
    BTN_ADD    (""),
    BTN_RETURN (""),
    BTN_SAVE   ("");

    // <<-FIELDS->>
    private String text;

    // <<-CONSTANTS->>
    private static final String PATH = Path.of(
            System.getProperty("user.dir"),  // Project directory
            "application",            // 'application' module
            "src", "main", "resources", "%s" // path from there
    ).toString();

    static {
        String localeFileName = AppConfiguration.getLocaleFileName();
        System.out.println(PATH);
        try (FileInputStream fileInputStream = new FileInputStream(String.format(PATH, localeFileName))) {
            Properties messagesProperties = new Properties();
            messagesProperties.load(fileInputStream);

            MAIN_FRAME_TITLE.text = (String) messagesProperties.get("contactbook.frame.title.main");
            ADD_FRAME_TITLE .text = (String) messagesProperties.get("contactbook.frame.title.add");
            INFO_FRAME_TITLE.text = (String) messagesProperties.get("contactbook.frame.title.info");

            CONTACT_BROWSER_DEFAULT.text = (String) messagesProperties.get("contactbook.frame.placeholder.browser.contacts");
            HOBBIES_BROWSER_DEFAULT.text = (String) messagesProperties.get("contactbook.frame.placeholder.browser.hobbies");
            HOBBIES_INPUT_DEFAULT  .text = (String) messagesProperties.get("contactbook.frame.placeholder.input.hobbies");

            BTN_REMOVE .text = (String) messagesProperties.get("contactbook.frame.btn.remove");
            BTN_CONSULT.text = (String) messagesProperties.get("contactbook.frame.btn.consult");
            BTN_ADD    .text = (String) messagesProperties.get("contactbook.frame.btn.add");
            BTN_RETURN .text = (String) messagesProperties.get("contactbook.frame.btn.return");
            BTN_SAVE   .text = (String) messagesProperties.get("contactbook.frame.btn.save");
        } catch (IOException ioE) {
            System.err.println("ERROR: IOException :: File NOT found");
            System.exit(1);
        }
    }

}
