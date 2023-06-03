package com.quathar.contactbook.ui.frame.helper;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

/**
 * <h1>To Be Specified</h1>
 *
 * @since 2022-04-07
 * @version 1.1
 * @author Q
 */
public class GBL {

    /**
     * Set the columnWeights and rowWeights of the GridBagLayout to 0.
     *
     * @param gridBagLayout the grid bag layout
     * @param columns the layout's number of columns
     * @param rows the layout's number of rows
     */
    private static void setWeightsToZero(GridBagLayout gridBagLayout, int columns, int rows) {
        gridBagLayout.columnWeights = new double[columns];
        gridBagLayout.rowWeights    = new double[rows];

        for (int i = 0; i < columns; i++)
            gridBagLayout.columnWeights[i] = 0;
        for (int i = 0; i < rows; i++)
            gridBagLayout.rowWeights[i] = 0;
    }

    /**
     * Creates a new GridBagLayout with the specified number of columns and rows.
     *
     * @param columns the layout's number of columns
     * @param rows the layout's number of rows
     * @param columnsToOne the columns that will have weights 1
     * @param rowsToOne the rows that will have weights 1
     *
     * @return a new grid bag layout configured
     */
    public static GridBagLayout createGridBagLayout(int columns, int rows, int[] columnsToOne, int[] rowsToOne) {
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths  = new int[columns];
        gridBagLayout.rowHeights    = new int[rows];
        setWeightsToZero(gridBagLayout, columns, rows);

        if (columnsToOne != null)
            for (int columnToOne : columnsToOne)
                gridBagLayout.columnWeights[columnToOne] = 1;
        if (rowsToOne != null)
            for (int rowToOne : rowsToOne)
                gridBagLayout.rowWeights[rowToOne] = 1;

        return gridBagLayout;
    }

    /**
     * Creates a new GridBagLayout with the specified number of columns and rows.
     * The columnWeights and rowWeights of all columns and rows will be 1.
     *
     * @param columns the layout's number of columns
     * @param rows the layout's number of rows
     *
     * @return a new grid bag layout configured
     */
    public static GridBagLayout createGridBagLayoutFull(int columns, int rows) {
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths  = new int[columns];
        gridBagLayout.columnWeights = new double[columns];
        gridBagLayout.rowHeights    = new int[rows];
        gridBagLayout.rowWeights    = new double[rows];

        for (int i = 0; i < columns; i++)
            gridBagLayout.columnWeights[i] = 1;
        for (int i = 0; i < rows; i++)
            gridBagLayout.rowWeights[i] = 1;

        return gridBagLayout;
    }

    /**
     * Creates new GridBagConstraints for a component by placing it in the specified position.
     *
     * @param x the horizontal position
     * @param y the vertical position
     *
     * @return the grid bag constraints with the specified position
     */
    public static GridBagConstraints createGridBagConstraints(int x, int y) {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = x;
        gridBagConstraints.gridy = y;
        return gridBagConstraints;
    }

}
