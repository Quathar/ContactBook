package com.quathar.contactbook.data.entity;

import com.quathar.contactbook.data.embeddable.Mail;
import com.quathar.contactbook.data.embeddable.Telephone;
import com.quathar.contactbook.data.enumerator.ContactType;
import com.quathar.contactbook.data.enumerator.Gender;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * <h1>Contact</h1>
 * <br>
 * Class used to store the information of a contact.
 *
 * @since 2022-04-03
 * @version 2.1
 * @author Q
 */
@Entity
@Table(name = "Contact")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Contact {

	// Basics
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String address;

	private String notes;

	private String surnames;

	@Enumerated(EnumType.STRING)
	private Gender gender;

	private String birthDate;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "contacts_telephones",
			joinColumns = @JoinColumn(name = "id"),
			foreignKey  = @ForeignKey(name = "FK_contacts_telephones"))
	private List<Telephone> telephones;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "contacts_mails",
			joinColumns = @JoinColumn(name = "id"),
			foreignKey  = @ForeignKey(name = "FK_contacts_mails"))
	private List<Mail> mails;

	@Enumerated(EnumType.STRING)
	private ContactType type;

	// Relations
	@ToString.Exclude
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "contact_hobby",
			joinColumns 	   = @JoinColumn(name = "contact_id"),
			foreignKey 		   = @ForeignKey(name = "FK_contacts"),
			inverseJoinColumns = @JoinColumn(name = "hobby_id"),
			inverseForeignKey  = @ForeignKey(name = "FK_hobbies"))
	private List<Hobby> hobbies;

}
