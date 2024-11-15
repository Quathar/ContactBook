package com.quathar.contactbook.ui.component;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import java.io.Serial;

/**
 * <h1>Changer ComboBox</h1>
 * <br>
 * JComboBox component that allows to change items in a simple way.
 *
 * @since 2022-05-08
 * @version 2.0
 * @author Q
 */
public class ChangerComboBox<T> extends JComboBox<T> {

    // <<-CONSTANT->>
    @Serial
    private static final long serialVersionUID = 1L;

    // <<-CONSTRUCTOR->>
    /**
     * Constructs a new ChangerComboBox with the specified items.
     *
     * @param items the items to populate the combo box.
     */
    public ChangerComboBox(T[] items) {
        newModel(items);
    }

    /**
     * Constructs a new ChangerComboBox with the specified ComboBoxModel.
     *
     * @param model the ComboBoxModel to use for the combo box.
     */
    public ChangerComboBox(ComboBoxModel<T> model) {
        super(model);
    }

    // <<-METHODS->>
    /**
     * Creates a new model with the specified elements and sets it as the model for the combo box.
     *
     * @param elements the elements to populate the combo box.
     */
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