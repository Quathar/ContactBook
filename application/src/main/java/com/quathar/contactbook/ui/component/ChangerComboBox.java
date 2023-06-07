package com.quathar.contactbook.ui.component;

import javax.swing.*;
import java.io.Serial;

/**
 * <h1>ChangerComboBox</h1>
 * <br>
 * JComboBox component that allows to change items in a simple way.
 * 
 * @since 2022-05-08
 * @version 2.0
 * @author Q
 */
public class ChangerComboBox<T> extends JComboBox<T> {

	// <<-CONSTANTS->>
	@Serial
	private static final long serialVersionUID = 1L;

	// <<-CONSTRUCTOR->>
	public ChangerComboBox(T[] items) {
		newModel(items);
	}

	public ChangerComboBox(ComboBoxModel<T> model) {
		super(model);
	}

	// <<-METHODS->>
	private void newModel(T[] elements) {
		setModel(new DefaultComboBoxModel<T>(elements));
	}
	
	/**
	 * Rename the items in the drop-down list.
	 * 
	 * @param elements the items to set
	 */
	public void changeList(T[] elements) {
		newModel(elements);
	}
	
}
