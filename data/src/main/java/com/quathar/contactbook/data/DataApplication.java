package com.quathar.contactbook.data;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.quathar.contactbook.data.config.DataConfiguration;
import com.quathar.contactbook.data.entity.Contact;
import com.quathar.contactbook.data.entity.Hobby;
import com.quathar.contactbook.data.enumerator.ContactType;
import com.quathar.contactbook.data.service.ContactService;
import jakarta.persistence.RollbackException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Path;
import java.util.List;


/**
 * <h1>DataApplication</h1>
 *
 * @since 2023-05-31
 * @version 1.0
 * @author Q
 */
public class DataApplication {

    // <<-CONSTANTS->>
    private static final Path CONTACTS_JSON_PATH = Path.of(System.getProperty("user.dir"), "data", "src", "main", "resources", "contacts.json");
    private static final Path HOBBIES_JSON_PATH = Path.of(System.getProperty("user.dir"), "data", "src", "main", "resources", "hobbies.json");

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
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Contact>>(){}.getType();

            List<Contact> contacts = gson.fromJson(reader, listType);

            Transaction transaction = session.beginTransaction();
            contacts.forEach(session::persist);
            transaction.commit();
        } catch (IOException ioE) {
            System.err.println("ERROR: IOException");
            System.exit(1);
        } catch (JsonSyntaxException jsE) {
            System.err.println("ERROR: JsonSyntaxException");
            System.exit(2);
        } catch (RollbackException rbE) {
            System.err.println("ERROR: RollbackException");
            System.exit(3);
        }
    }

    private static void loadHobbiesInitData() {
        try (
                SessionFactory sessionFactory = getConfiguration().buildSessionFactory();
                Session        session        = sessionFactory.openSession();
                Reader         reader         = new FileReader(HOBBIES_JSON_PATH.toString())
        ) {
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Hobby>>(){}.getType();

            List<Hobby> hobbies = gson.fromJson(reader, listType);

            Transaction transaction = session.beginTransaction();
            for (Hobby hobby : hobbies)
                session.persist(hobby);
            transaction.commit();
        } catch (IOException ioE) {
            System.err.println("ERROR: IOException");
            System.exit(1);
        } catch (JsonSyntaxException jsE) {
            System.err.println("ERROR: JsonSyntaxException");
            System.exit(2);
        } catch (RollbackException rbE) {
            System.err.println("ERROR: RollbackException");
            System.exit(3);
        }
    }

    public static void main(String[] args) {
        // Uncomment to store more test data (change ddl to create in hibernate.properties to reset ID)
//        loadContactsInitData();
//        loadHobbiesInitData();

        // Test
        test();
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

}
