package com.quathar.contactbook.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * <h1>Hobby</h1>
 * 
 * @since 2022-04-03
 * @version 2.0
 * @author Q
 */
@Entity
@Table(name = "hobbies")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Hobby {

	// <<-FIELDS->>

	// Basics
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "hobby")
	private String name;

	// Relations
	@ToString.Exclude
	@ManyToMany(mappedBy = "hobbies", fetch = FetchType.EAGER)
	private List<Contact> contacts;

}
