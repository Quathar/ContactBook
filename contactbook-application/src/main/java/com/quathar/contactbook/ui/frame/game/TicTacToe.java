package com.quathar.contactbook.ui.frame.game;

import com.quathar.contactbook.Application;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.io.Serial;

/**
 * <h1>TicTacToe</h1>
 * Tic-tac-toe game with graphical environment for the User interface (UI).
 *
 * @since 2022-04-01
 * @version 1.0
 * @author Q
 */
public class TicTacToe extends JFrame {

    // <<-CONSTANTS->>
    @Serial
    private static final long serialVersionUID = 1L;
    private static final String FRAME_TITLE = "Tic Tac Toe";
    private static final int LINE = 3;
    private static final String WINNING_MESSAGE = "YOU HAVE WON... GAME OVER";
    private static final String LOSING_MESSAGE = "YOU HAVE LOST... GAME OVER";
    private static final String TIE_MESSAGE = "TIE... GAME OVER";

    // <<-FIELDS->>
    private JPanel contentPane;
    private JButton[][] buttons;
    private final String human;
    private final String robot;

    // <<-CONSTRUCTORS->>
    public TicTacToe(String human, String robot) {
        super(FRAME_TITLE);
        drawBoard();
        this.human = human;
        this.robot = robot;
        if (Math.random() < 0.5) turnCPU();
    }

    // <<-METHODS->>
    // ========================
    // = = = Design Zone = = =
    // = = = Design Zone = = =
    // = = = Design Zone = = =
    // ========================
    private void drawBoard() {
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setBounds((int) (Application.SCREEN_SIZE.width  * 0.40),
                  (int) (Application.SCREEN_SIZE.height * 0.40),
                  300,
                  300);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setLayout(new GridLayout(LINE, LINE));
        setContentPane(contentPane);

        buttons = new JButton[LINE][LINE];
        for (int i = 0; i < LINE; i++)
            for (int j = 0; j < LINE; j++) {
                buttons[i][j] = new JButton();
                int finalI = i;
                int finalJ = j;
                buttons[i][j].addActionListener(e -> {
                    occupyButton(finalI, finalJ, human);
                    turnCPU();
                    if (hasCompleted())
                        end();
                });
                contentPane.add(buttons[i][j]);
            }
    }

    // M�TODOS
    private void occupyButton(int x, int y, String ficha) {
        buttons[x][y].setText(ficha);
        buttons[x][y].setEnabled(false);
    }

    private void turnCPU() {
        if (!full())
            while (!pressCPU());
    }

    private boolean full() {
        for (int i = 0; i < LINE; i++) {
            for (int j = 0; j < LINE; j++) {
                if (buttons[i][j].getText().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean pressCPU() {
        int x = (int) (Math.random() * LINE);
        int y = (int) (Math.random() * LINE);

        if (buttons[x][y].getText().isEmpty()) {
            occupyButton(x, y, robot);
            return true;
        }
        return false;
    }

    private boolean hasCompleted() {
        return full() || hasWon(human) || hasWon(robot);
    }

    private void end() {
        if (hasWon(human))
             JOptionPane.showMessageDialog(contentPane, WINNING_MESSAGE);
        else if (hasWon(robot))
             JOptionPane.showMessageDialog(contentPane, LOSING_MESSAGE);
        else JOptionPane.showMessageDialog(contentPane, TIE_MESSAGE);
        dispose();
    }

    private boolean hasWon(String chip) {
        int counter;

        // Check columns
        for (int i = 0; i < LINE; i++) {
            counter = 0;
            for (int j = 0; j < LINE; j++)
                if (buttons[i][j].getText().equals(chip))
                    counter++;
            if (counter == LINE)
                return true;
        }

        // Check rows
        for (int i = 0; i < LINE; i++) {
            counter = 0;
            for (int j = 0; j < LINE; j++)
                if (buttons[j][i].getText().equals(chip))
                    counter++;
            if (counter == LINE)
                return true;
        }

        // Check 1st diagonal
        counter = 0;
        for (int i = 0; i < LINE; i++)
            if (buttons[i][i].getText().equals(chip))
                counter++;
        if (counter == LINE)
            return true;

        // Check 2nd diagonal
        counter = 0;
        int j = LINE - 1;
        for (int i = 0; i < LINE; i++) {
            if (buttons[i][j].getText().equals(chip))
                counter++;
            j--;
        }

        return counter == LINE;
    }

}
