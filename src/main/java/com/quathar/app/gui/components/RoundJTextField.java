package com.quathar.app.gui.components;

import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JTextField;

/**
 * Clase utilizada para crear JTextField redondeados.
 * 
 * @see JTextField
 * @since 15-12-2011
 * @author Harry Joy
 */
public class RoundJTextField extends JTextField { // CLASE FINALIZADA
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Forma del componente.
	 */
	private Shape shape;
	
	// CONSTRUCTORES
	/**
	 * Constructor.
	 */
	public RoundJTextField() {
        setOpaque(false);
    }
	
	/**
	 * Constructor.
	 * 
	 * @param size the width of the component
	 */
    public RoundJTextField(int size) {
        super(size);
        setOpaque(false);
    }
    
    /**
     * Constructor.
     * 
     * @param text the text of the component
     */
    public RoundJTextField(String text) {
        super(text);
        setOpaque(false);
    }
    
    // OVERRIDE
    @Override
    protected void paintComponent(Graphics g) {
         g.setColor(getBackground());
         g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
         super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
         g.setColor(getForeground());
         g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
    }

    @Override
    public boolean contains(int x, int y) {
         if (shape == null || !shape.getBounds().equals(getBounds())) {
             shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight()-1, 15, 15);
         }
         return shape.contains(x, y);
    }
    
}
