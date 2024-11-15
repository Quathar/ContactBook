package com.quathar.contactbook.data;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.google.inject.Guice;
import com.google.inject.Injector;

import com.quathar.contactbook.data.config.DataConfiguration;
import com.quathar.contactbook.data.entity.Contact;
import com.quathar.contactbook.data.entity.Hobby;
import com.quathar.contactbook.data.enumerator.ContactType;
import com.quathar.contactbook.data.service.ContactService;
import com.quathar.contactbook.data.service.HobbyService;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import jakarta.persistence.RollbackException;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Path;
import java.util.List;

/**
 * <h1>DataApplication</h1>
 * <br>
 * <p>
 *     Class used for inserting test data into the database and for basic testing.<br>
 *     It is also formats the data.
 * </p>
 *
 * @since 2023-05-31
 * @version 1.0
 * @author Q
 */
public class DataApplication {

    // <<-CONSTANTS->>
    private static final Path CONTACTS_JSON_PATH = Path.of(
            System.getProperty("user.dir"), "contactbook-data",
            "src", "main", "resources", "json", "contacts.json");
    private static final Path HOBBIES_JSON_PATH = Path.of(
            System.getProperty("user.dir"), "contactbook-data",
            "src", "main", "resources", "json", "hobbies.json");
    private static final Path RELATIONS_JSON_PATH = Path.of(
            System.getProperty("user.dir"), "contactbook-data",
            "src", "main", "resources", "json", "contacts_hobbies.json");

    // <<-METHODS->>
    private static Configuration getConfiguration() {
        Injector injector = Guice.createInjector(new DataConfiguration());
        return injector.getInstance(Configuration.class);
    }

    private static void loadContactsInitData() {
        try (
                SessionFactory sessionFactory = getConfiguration().buildSessionFactory();
                Session        session        = sessionFactory.openSession();
                Reader         reader         = new FileReader(CONTACTS_JSON_PATH.toString())
        ) {
            Type listType = new TypeToken<List<Contact>>(){}.getType();

            List<Contact> contacts = new Gson().fromJson(reader, listType);

            Transaction transaction = session.beginTransaction();
            contacts.forEach(session::persist);
            transaction.commit();
        } catch (IOException ioE) {
            System.err.println("ERROR: IOException");
            System.exit(1);
        } catch (RollbackException rbE) {
            System.err.println("ERROR: RollbackException");
            System.exit(2);
        } catch (JsonSyntaxException jsE) {
            System.err.println("ERROR: JsonSyntaxException");
            System.exit(3);
        }
    }

    private static void loadHobbiesInitData() {
        try (
                SessionFactory sessionFactory = getConfiguration().buildSessionFactory();
                Session        session        = sessionFactory.openSession();
                Reader         reader         = new FileReader(HOBBIES_JSON_PATH.toString())
        ) {
            Type listType = new TypeToken<List<Hobby>>(){}.getType();

            List<Hobby> hobbies = new Gson().fromJson(reader, listType);

            Transaction transaction = session.beginTransaction();
            for (Hobby hobby : hobbies)
                session.persist(hobby);
            transaction.commit();
        } catch (IOException ioE) {
            System.err.println("ERROR: IOException");
            System.exit(1);
        } catch (RollbackException jsE) {
            System.err.println("ERROR: RollbackException");
            System.exit(2);
        } catch (JsonSyntaxException rbE) {
            System.err.println("ERROR: JsonSyntaxException");
            System.exit(3);
        }
    }

    private static void loadContactsHobbiesRelations() {
        try (
                SessionFactory sessionFactory = getConfiguration().buildSessionFactory();
                Session        session        = sessionFactory.openSession();
                Reader         reader         = new FileReader(RELATIONS_JSON_PATH.toString())
        ) {
            Type listType = new TypeToken<List<Relation>>(){}.getType();

            List<Relation> relations = new Gson().fromJson(reader, listType);

            for (Relation relation : relations) {
                session.beginTransaction();
                Contact contact = session.get(Contact.class, relation.getContactId());
                List<Hobby> hobbies = contact.getHobbies();
                hobbies.add(session.get(Hobby.class, relation.getHobbyId()));
                session.persist(contact);
                session.getTransaction()
                       .commit();
            }
        } catch (IOException ioE) {
            System.err.println("ERROR: IOException");
            System.exit(1);
        } catch (RollbackException jsE) {
            System.err.println("ERROR: RollbackException");
            System.exit(2);
        } catch (JsonSyntaxException rbE) {
            System.err.println("ERROR: JsonSyntaxException");
            System.exit(3);
        }
    }

    /**
     * Delete all the info from the database
     */
    public static void format() {
        Injector injector = Guice.createInjector(new DataConfiguration());
        injector.getInstance(ContactService.class)
                .deleteAll();
        injector.getInstance(HobbyService.class)
                .deleteAll();
    }

    private static void loadData() {
        loadContactsInitData();
        loadHobbiesInitData();
        loadContactsHobbiesRelations();
    }

    public static void main(String[] args) {
        // Uncomment to store more test data
        // (change hibernate.hbm2ddl to 'create' in hibernate.properties to reset ID)
        loadData();

        // Testing
//        test();
    }

    private static void test() {
        Injector injector = Guice.createInjector(new DataConfiguration());
        ContactService contactService = injector.getInstance(ContactService.class);

        int num = 25;
        // GET ALL
        System.out.println("=".repeat(num));
        System.out.println("ALL CONTACTS");
        System.out.println("=".repeat(num));
        contactService.getAll()
                      .forEach(System.out::println);

        // GET BY ID
        System.out.println("=".repeat(num));
        System.out.println("CONTACT WITH ID 4");
        System.out.println("=".repeat(num));
        System.out.println(contactService.getById(4L));

        // GET ALL BY PARAMS (type, name)
        System.out.println("=".repeat(num));
        System.out.println("ALL PET CONTACTS");
        System.out.println("=".repeat(num));
        contactService.getAllByParams(ContactType.PET, null)
                      .forEach(System.out::println);
    }

    /**
     * <h1>Relation</h1>
     * <br>
     * Nested class to insert the contacts_hobbies relations to initial test data.
     *
     * @since 2023-06-06
     * @version 1.0
     * @author Q
     */
    @AllArgsConstructor
    @Getter
    @ToString
    class Relation {

        // <<-FIELDS->>
        @SerializedName("contact_id")
        private Long contactId;
        @SerializedName("hobby_id")
        private Long hobbyId;

    }

}
