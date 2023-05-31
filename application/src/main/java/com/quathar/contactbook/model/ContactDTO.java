package com.quathar.contactbook.model;

import com.quathar.contactbook.ui.table.HobbyTable;
import com.quathar.contactbook.ui.table.MailTable;
import com.quathar.contactbook.ui.table.TelephoneTable;
import com.toedter.calendar.JDateChooser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @since 2023-05-30
 * @version 1.0
 * @author Q
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ContactDTO {

	// <<-FIELDS->>
	private JComboBox<String> typeComboBox;
	private JTextField nameTextField;
	private JTextField surnamesTextField;
	private JTextField addressTextField;
	private JDateChooser birthDateChooser;
	private JComboBox<String> genderComboBox;
	private JTextArea notesTextArea;

	private HobbyTable hobbyTable;
	private TelephoneTable telephoneTable;
	private MailTable mailTable;

}
