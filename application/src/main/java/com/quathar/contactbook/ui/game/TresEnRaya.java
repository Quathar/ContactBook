import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//package com.quathar.contactbook.ui.game;
//
//import com.quathar.app.gui.frame.ContactBook;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
///**
// * Juego del Tres en raya con entorno gr�fico de la GUI.
// *
// * @since 2022-04-01
// * @author Q
// */
public class TresEnRaya extends JFrame implements ActionListener { // CLASE FINALIZADA
//
//	// CONSTANTES
//	private static final long serialVersionUID = 1L;
//	/**
//	 * N�mero de filas y columnas del tablero.
//	 */
//	private static final int LINE = 3;
//
//	// CAMPOS
//	/**
//	 * ContentPane del JFrame.
//	 */
//	private JPanel contentPane;
//	/**
//	 * Botones del tablero.
//	 */
//	private JButton[][] buttons = new JButton[LINE][LINE];
//	/**
//	 * Fichas de los participantes.
//	 */
//	private String human, robot;
//
//	// CONSTRUCTOR
//	/**
//	 * Constructor.
//	 *
//	 * @param human mark of player 1 (human)
//	 * @param robot mark of player 2 (robot)
//	 */
//	public TresEnRaya(String human, String robot) {
//		createBoard();
//		this.human = human;
//		this.robot = robot;
//		if (Math.random() < 0.5)
//			turnoComputadora();
//	}
//
//	// <<ZONA DE DISE�O>>
//	/**
//	 * Crea el tablero.
//	 */
//	private void createBoard() {
//		setTitle(LINE + " EN RAYA");
//		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
//		setBounds(	(int) (ContactBook.ScreenSize.width * 0.422),
//					(int) (ContactBook.ScreenSize.height * 0.4),
//					(int) 300,
//					(int) 300 );
//		setResizable(false);
//
//		contentPane = new JPanel();
//		contentPane.setLayout(new GridLayout(LINE, LINE));
//		setContentPane(contentPane);
//
//		for (int i = 0; i < LINE; i++)
//			for (int j = 0; j < LINE; j++) {
//				buttons[i][j] = new JButton();
//				buttons[i][j].addActionListener(this);
//				contentPane.add(buttons[i][j]);
//			}
//	}
//
//	// M�TODOS
//	private void ocuparBoton(int x, int y, String ficha) {
//		buttons[x][y].setText(ficha);
//		buttons[x][y].setEnabled(false);
//	}
//
//	private void turnoComputadora() {
//		if (!lleno()) {
//			while (!pulsaComputadora());
//		}
//	}
//
//	private boolean lleno() {
//		for (int i = 0; i < LINE; i++) {
//			for (int j = 0; j < LINE; j++) {
//				if (buttons[i][j].getText().isEmpty()) {
//					return false;
//				}
//			}
//		}
//		return true;
//	}
//
//	private boolean pulsaComputadora() {
//		int x = (int) (Math.random() * LINE);
//		int y = (int) (Math.random() * LINE);
//
//		if (buttons[x][y].getText().isEmpty()) {
//			ocuparBoton(x, y, robot);
//			return true;
//		}
//		return false;
//	}
//
//	private boolean haFinalizado() {
//		return lleno() || haGanado(human) || haGanado(robot);
//	}
//
//	private void finalizar() {
//		if (haGanado(human))
//			JOptionPane.showMessageDialog(contentPane, "HAS GANADO... FIN DEL JUEGO");
//		else if (haGanado(robot))
//			JOptionPane.showMessageDialog(contentPane, "HAS PERDIDO... FIN DEL JUEGO");
//		else
//			JOptionPane.showMessageDialog(contentPane, "EMPATE... FIN DEL JUEGO");
//		dispose();
//	}
//
//	private boolean haGanado(String ficha) {
//		int contador;
//
//		// Comprueba las columnas
//		for (int i = 0; i < LINE; i++) {
//			contador = 0;
//			for (int j = 0; j < LINE; j++) {
//				if (buttons[i][j].getText() == ficha) {
//					contador++;
//				}
//			}
//
//			if (contador == LINE) {
//				return true;
//			}
//		}
//
//		// Comprueba las filas
//		for (int i = 0; i < LINE; i++) {
//			contador = 0;
//			for (int j = 0; j < LINE; j++) {
//				if (buttons[j][i].getText() == ficha) {
//					contador++;
//				}
//			}
//
//			if (contador == LINE) {
//				return true;
//			}
//		}
//
//		// Comprobar 1� diagonal
//		contador = 0;
//		for (int i = 0; i < LINE; i++) {
//			if (buttons[i][i].getText() == ficha) {
//				contador++;
//			}
//		}
//
//		if (contador == LINE) {
//			return true;
//		}
//
//		// Comprobar 2� diagonal
//		contador = 0;
//		int j = LINE - 1;
//		for (int i = 0; i < LINE; i++) {
//			if (buttons[i][j].getText() == ficha) {
//				contador++;
//			}
//			j--;
//		}
//
//		if (contador == LINE) {
//			return true;
//		}
//
//		return false;
//	}
//
	// <<ZONAS DE LISTENERS>>
	@Override
	public void actionPerformed(ActionEvent e) {
//		for (int i = 0; i < LINE; i++)
//			for (int j = 0; j < LINE; j++)
//				if (e.getSource() == buttons[i][j]) {
//					ocuparBoton(i, j, human);
//					turnoComputadora();
//				}
//
//		if (haFinalizado())
//			finalizar();
	}

}
