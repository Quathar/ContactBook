package com.quathar.contactbook.ui.frame.i18n;

import com.quathar.contactbook.config.LocaleConfig;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Properties;

/**
 * <h1>Message</h1>
 *
 * @since 2023-06-21
 * @version 1.0
 * @author Q
 */
@AllArgsConstructor
@Getter
public enum Message {

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
    BTN_SAVE   (""),
    PLACEHOLDER_NAME(""),
    PLACEHOLDER_SURNAMES(""),
    PLACEHOLDER_ADDRESS(""),
    PLACEHOLDER_NOTES(""),
    PLACEHOLDER_TELEPHONE(""),
    PLACEHOLDER_MAIL("");

    // <<-FIELDS->>
    private String text;

    // <<-CONSTANTS->>
    private static final Path PATH = Path.of(
            System.getProperty("user.dir"), "contactbook-application",
            "src", "main", "resources", "%s");

    static {
        String localeFileName = LocaleConfig.getLocaleFileName();
        try (FileInputStream fileInputStream = new FileInputStream(String.format(PATH.toString(), localeFileName))) {
            Properties messagesProperties = new Properties();
            messagesProperties.load(fileInputStream);

            MAIN_FRAME_TITLE.text = (String) messagesProperties.get("contactbook.frame.main.title");
            ADD_FRAME_TITLE .text = (String) messagesProperties.get("contactbook.frame.add.title");
            INFO_FRAME_TITLE.text = (String) messagesProperties.get("contactbook.frame.info.title");

            CONTACT_BROWSER_DEFAULT.text = (String) messagesProperties.get("contactbook.frame.main.placeholder.browser.contacts");
            HOBBIES_BROWSER_DEFAULT.text = (String) messagesProperties.get("contactbook.frame.main.placeholder.browser.hobbies");
            HOBBIES_INPUT_DEFAULT  .text = (String) messagesProperties.get("contactbook.frame.main.placeholder.input.hobbies");

            BTN_REMOVE .text = (String) messagesProperties.get("contactbook.frame.btn.remove");
            BTN_CONSULT.text = (String) messagesProperties.get("contactbook.frame.btn.consult");
            BTN_ADD    .text = (String) messagesProperties.get("contactbook.frame.btn.add");
            BTN_RETURN .text = (String) messagesProperties.get("contactbook.frame.btn.return");
            BTN_SAVE   .text = (String) messagesProperties.get("contactbook.frame.btn.save");

            PLACEHOLDER_NAME     .text = (String) messagesProperties.get("contactbook.frame.add.placeholder.name");
            PLACEHOLDER_SURNAMES .text = (String) messagesProperties.get("contactbook.frame.add.placeholder.surnames");
            PLACEHOLDER_ADDRESS  .text = (String) messagesProperties.get("contactbook.frame.add.placeholder.address");
            PLACEHOLDER_NOTES    .text = (String) messagesProperties.get("contactbook.frame.add.placeholder.notes");
            PLACEHOLDER_TELEPHONE.text = (String) messagesProperties.get("contactbook.frame.add.placeholder.telephone");
            PLACEHOLDER_MAIL     .text = (String) messagesProperties.get("contactbook.frame.add.placeholder.mail");
        } catch (IOException ioE) {
            System.err.println("ERROR: IOException :: File NOT found");
            System.exit(1);
        }
    }

}