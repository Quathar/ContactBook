package com.quathar.contactbook.data;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.google.inject.Guice;
import com.google.inject.Injector;

import com.quathar.contactbook.data.config.DaoInjector;
import com.quathar.contactbook.data.config.DataConfiguration;
import com.quathar.contactbook.data.dao.ContactDao;
import com.quathar.contactbook.data.dao.HobbyDao;
import com.quathar.contactbook.data.entity.Contact;
import com.quathar.contactbook.data.entity.Hobby;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

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
public class DataLoader {

    // <<-CONSTANTS->>
    private static final Path CONTACTS_JSON_PATH = Path.of(
            System.getProperty("project.rootDir"), "contactbook-data",
            "src", "main", "resources", "json", "contacts.json");
    private static final Path HOBBIES_JSON_PATH = Path.of(
            System.getProperty("project.rootDir"), "contactbook-data",
            "src", "main", "resources", "json", "hobbies.json");
    private static final Path RELATIONS_JSON_PATH = Path.of(
            System.getProperty("project.rootDir"), "contactbook-data",
            "src", "main", "resources", "json", "contacts_hobbies.json");

    // <<-METHODS->>
    private static void loadContactsData(Session session) throws IOException {
        try (Reader reader = new FileReader(CONTACTS_JSON_PATH.toString())) {
            Type listType = new TypeToken<List<Contact>>(){}.getType();
            List<Contact> contacts = new Gson().fromJson(reader, listType);
            contacts.forEach(session::persist);
            session.flush();
        }
    }

    private static void loadHobbiesData(Session session) throws IOException {
        try (Reader reader = new FileReader(HOBBIES_JSON_PATH.toString())) {
            Type listType = new TypeToken<List<Hobby>>(){}.getType();
            List<Hobby> hobbies = new Gson().fromJson(reader, listType);
            for (Hobby hobby : hobbies)
                session.persist(hobby);
        }
    }

    private static void loadContactsHobbiesRelations(Session session) throws IOException {
        try (Reader reader = new FileReader(RELATIONS_JSON_PATH.toString())) {
            Type listType = new TypeToken<List<Relation>>(){}.getType();
            List<Relation> relations = new Gson().fromJson(reader, listType);

            relations.stream()
                     .collect(Collectors.groupingBy(Relation::getContactId))
                     .entrySet()
                     .parallelStream()
                     .forEach(entry -> {
                         var contactId = entry.getKey();
                         Contact contact = session.get(Contact.class, contactId);
                         if (contact == null) {
                             return;
                         }

                         List<Hobby> hobbyList = entry.getValue()
                                                      .parallelStream()
                                                      .map(e -> session.get(Hobby.class, e.getHobbyId()))
                                                      .collect(Collectors.toList());
                         contact.setHobbies(hobbyList);
                         session.merge(contact);
                     });
        }
    }

    /**
     * Delete all the info from the database
     */
    public static void format() {
        Injector injector = Guice.createInjector(new DaoInjector());
        injector.getInstance(ContactDao.class)
                .deleteAll();
        injector.getInstance(HobbyDao.class)
                .deleteAll();
    }

    public static void load() {
        Injector injector = Guice.createInjector(new DataConfiguration());
        Configuration configuration = injector.getInstance(Configuration.class);
        try (
                SessionFactory sessionFactory = configuration.buildSessionFactory();
                Session session = sessionFactory.openSession()
        ) {
            Transaction transaction = session.beginTransaction();

            // Uncomment to store more test data
            // (change hibernate.hbm2ddl to 'create' in hibernate.properties to reset ID)
            loadContactsData(session);
            loadHobbiesData(session);
            loadContactsHobbiesRelations(session);

            transaction.commit();
        } catch (IOException e) {
            System.err.println("ERROR: IOException");
            System.exit(1);
        }
    }

    /**
     * <h1>Relation</h1>
     * 
     * Nested class to insert the contacts_hobbies relations to initial test data.
     *
     * @since 2023-06-06
     * @version 1.0
     * @author Q
     */
    @AllArgsConstructor
    @Getter
    @ToString
    static class Relation {

        @SerializedName("contact_id")
        private Long contactId;

        @SerializedName("hobby_id")
        private Long hobbyId;

    }

}
