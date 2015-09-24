
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JToolBar;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Classe che estende Jframe e genera una finestra di Login per accedere all'interfaccia dell'applicazione.
 * @author Giulia Santini
 * @version 1.1
 *
 */
public class Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldUsername;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Costruttore della classe Login.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panelFieldLogin = new JPanel();
		panelFieldLogin.setBounds(0, 0, 434, 212);
		contentPane.add(panelFieldLogin);
		panelFieldLogin.setLayout(null);

		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUsername.setBounds(33, 55, 80, 22);
		panelFieldLogin.add(lblUsername);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPassword.setBounds(33, 107, 80, 17);
		panelFieldLogin.add(lblPassword);

		textFieldUsername = new JTextField();
		textFieldUsername.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textFieldUsername.setBounds(156, 58, 205, 20);
		panelFieldLogin.add(textFieldUsername);
		textFieldUsername.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		passwordField.setBounds(156, 107, 205, 20);
		panelFieldLogin.add(passwordField);

		JPanel panelBottoneLogin = new JPanel();
		panelBottoneLogin.setBounds(0, 213, 434, 48);
		contentPane.add(panelBottoneLogin);
		panelBottoneLogin.setLayout(null);

		JToolBar toolBarBottoneLogin = new JToolBar();
		toolBarBottoneLogin.setBounds(0, 0, 434, 48);
		panelBottoneLogin.add(toolBarBottoneLogin);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Memorizza il valore di input dei campi username e password
				String user = textFieldUsername.getText();
				char[] pwd = passwordField.getPassword();

				//Converte l'array di caratteri in Stringa
				String s = new String (pwd);

				//Controllo se i valori inseriti corrispondono allo username e password della classe Utente
				//In caso positivo, viene mandata in esecuzione l'interfaccia rubrica
				if(Utente.getUsername().equals(user)&&Utente.getPassword().equals(s))
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								InterfacciaRubrica frame = new InterfacciaRubrica();
								frame.setVisible(true);
								setVisible(false);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});

				//Altrimenti, genera un messaggio di login errato
				else {
					JOptionPane.showMessageDialog(null, "Username o password inseriti errati!", "Errore", JOptionPane.INFORMATION_MESSAGE);
				}


			}
		});
		btnLogin.setFont(new Font("Times New Roman", Font.BOLD, 15));
		toolBarBottoneLogin.add(btnLogin);
	}
}
