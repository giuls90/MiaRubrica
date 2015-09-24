

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JToolBar;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.awt.event.ActionEvent;

/**
 * Classe che estende Jframe e genera una finestra per l'inserimento o modifica dei campi di un determinato contatto della rubrica.
 * @author Giulia Santini
 * @version 2.1
 */
public class EditorPersona extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNome;
	private JTextField textFieldCognome;
	private JTextField textFieldIndirizzo;
	private JTextField textFieldTelefono;
	private JTextField textFieldEta;


	/**
	 * Metodo che restituisce il testo inserito nel jtextfield corrispondente al nome.
	 * @return textFieldNome
	 */
	public JTextField getTextFieldNome() {
		return textFieldNome;
	}



	/**
	 * Metodo che restituisce il testo inserito nel jtextfield corrispondente al cognome.
	 * @return textFieldCognome
	 */
	public JTextField getTextFieldCognome() {
		return textFieldCognome;
	}



	/**
	 * Metodo che restituisce il testo inserito nel jtextfield corrispondente all'indirizzo.
	 * @return textFieldIndirizzo
	 */
	public JTextField getTextFieldIndirizzo() {
		return textFieldIndirizzo;
	}



	/**
	 * Metodo che restituisce il testo inserito nel jtextfield corrispondente al telefono.
	 * @return textFieldTelefono
	 */
	public JTextField getTextFieldTelefono() {
		return textFieldTelefono;
	}



	/**
	 * Metodo che restituisce il testo inserito nel jtextfield corrispondente all'età.
	 * @return textFieldEta
	 */
	public JTextField getTextFieldEta() {
		return textFieldEta;
	}



	
	/**
	 * Costruttore del frame EditorPersona.
	 * @param miaRubrica Oggetto di tipo Rubrica.
	 * @param home Oggetto di tipo InterfacciaRubrica.
	 * @param tag Indice intero.
	 */
	public EditorPersona(final Rubrica miaRubrica, final InterfacciaRubrica home, final int tag) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panelFieldEditor = new JPanel();
		panelFieldEditor.setBounds(0, 0, 434, 212);
		contentPane.add(panelFieldEditor);
		panelFieldEditor.setLayout(null);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNome.setBounds(10, 32, 46, 17);
		panelFieldEditor.add(lblNome);

		JLabel lblCognome = new JLabel("Cognome");
		lblCognome.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblCognome.setBounds(10, 60, 59, 27);
		panelFieldEditor.add(lblCognome);

		JLabel lblIndirizzo = new JLabel("Indirizzo");
		lblIndirizzo.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblIndirizzo.setBounds(10, 97, 59, 14);
		panelFieldEditor.add(lblIndirizzo);

		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblTelefono.setBounds(10, 129, 59, 14);
		panelFieldEditor.add(lblTelefono);

		JLabel lblEta = new JLabel("Eta");
		lblEta.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblEta.setBounds(10, 162, 46, 14);
		panelFieldEditor.add(lblEta);

		textFieldNome = new JTextField();
		textFieldNome.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textFieldNome.setBounds(128, 31, 296, 20);
		panelFieldEditor.add(textFieldNome);
		textFieldNome.setColumns(10);

		textFieldCognome = new JTextField();
		textFieldCognome.setBounds(128, 64, 296, 20);
		panelFieldEditor.add(textFieldCognome);
		textFieldCognome.setColumns(10);

		textFieldIndirizzo = new JTextField();
		textFieldIndirizzo.setBounds(128, 95, 296, 20);
		panelFieldEditor.add(textFieldIndirizzo);
		textFieldIndirizzo.setColumns(10);

		textFieldTelefono = new JTextField();
		textFieldTelefono.setBounds(128, 127, 296, 20);
		panelFieldEditor.add(textFieldTelefono);
		textFieldTelefono.setColumns(10);

		textFieldEta = new JTextField();
		textFieldEta.setBounds(128, 160, 296, 20);
		panelFieldEditor.add(textFieldEta);
		textFieldEta.setColumns(10);

		JPanel panelBottoniEditor = new JPanel();
		panelBottoniEditor.setBounds(0, 211, 434, 50);
		contentPane.add(panelBottoniEditor);
		panelBottoniEditor.setLayout(null);

		JToolBar toolBarEditor = new JToolBar();
		toolBarEditor.setBounds(0, 11, 434, 39);
		panelBottoniEditor.add(toolBarEditor);

		JButton btnSalva = new JButton("Salva");
		btnSalva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String nome = textFieldNome.getText();
				String cognome = textFieldCognome.getText();
				String indirizzo = textFieldIndirizzo.getText();
				String telefono = textFieldTelefono.getText();
				boolean t= true;

				//Se non sono stati compilati tutti i campi
				if (nome.equals("")||cognome.equals("")||indirizzo.equals("")|| telefono.equals("")) 
					//Genera messaggio di errore
					JOptionPane.showMessageDialog(null,"Completare correttamente tutti i campi!", "Errore",
							JOptionPane.INFORMATION_MESSAGE);

				else {
					//Controllo che sia stato inserito un intero, nel campo età
					try {
						//Converte l'input del campo età, che è un oggetto String in un intero
						int eta=Integer.parseInt( textFieldEta.getText());

						//Se il nome o il cognome inseriti, corrispondono a un contatto già presente nella rubrica,setto il tag t a false
						for (Persona p: miaRubrica.getElencoContatti()) 
							if (nome.equals(p.getNome()) && cognome.equals(p.getCognome())) {
								t=false;
							}	
						
						//Se il tag=-1, significa che devo "Aggiungere" un contatto
						if (tag==-1) {
							//Se t=false, errore
							if (t==false){
								JOptionPane.showMessageDialog(null,"Il contatto è già presente nella rubrica!", "Errore",
										JOptionPane.INFORMATION_MESSAGE);
								setVisible(false);
							} 
							
							else {
								miaRubrica.creaContatto(nome,cognome,indirizzo,telefono,eta);
								GestoreFile.aggiungiFile(nome,cognome,indirizzo, telefono,eta);
							}

						}
						
						//Se il tag è diverso da -1, allora si tratta di un'operazione di "Modifica" di un contatto
						if (tag!=-1) {
							Persona p = miaRubrica.getElencoContatti().get(tag);
							//Controllo che non esistano altri contatti (a parte il contatto che voglio modificare) che non abbiano lo stesso
							//nome e cognome di quelli inseriti
							for (Persona pers: miaRubrica.getElencoContatti())
								if (!pers.equals(p))
									if (nome.equals(pers.getNome()) && cognome.equals(pers.getCognome())) 
										t=false;
							if (t) {
							//Salvo i vecchi valori di nome e cognome del file
							String n = miaRubrica.getElencoContatti().get(tag).getNome();
							String c =miaRubrica.getElencoContatti().get(tag).getCognome();
							//elimino il file
							GestoreFile.eliminaFile(n, c);
							//modifico il contatto nell'array
							miaRubrica.modificaContatto(tag,nome, cognome, indirizzo, telefono,eta);
							//Genero un nuovo file
							GestoreFile.aggiungiFile(nome, cognome, indirizzo, telefono, eta);
							}
							else {
								JOptionPane.showMessageDialog(null,"Il contatto è già presente nella rubrica!", "Errore",
										JOptionPane.INFORMATION_MESSAGE);
								setVisible(false);
							}
							}
					

					} catch(NumberFormatException exc){
						JOptionPane.showMessageDialog(null,"Inserire un numero nel campo Età!", "Errore",
								JOptionPane.INFORMATION_MESSAGE);
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 

				} 


				home.aggiornaTabella();

				setVisible(false);
			}
		});

		btnSalva.setIcon(new ImageIcon("C:\\Users\\Giulia\\EEEworkspace\\JavaWorkspace\\MiaRubrica\\Immagini\\salva2.png"));
		btnSalva.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnSalva.setForeground(Color.BLUE);
		toolBarEditor.add(btnSalva);

		JButton btnAnnulla = new JButton("Annulla");
		btnAnnulla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);

			}
		});

		btnAnnulla.setIcon(new ImageIcon("C:\\Users\\Giulia\\EEEworkspace\\JavaWorkspace\\MiaRubrica\\Immagini\\annulla2.png"));
		btnAnnulla.setForeground(Color.RED);
		btnAnnulla.setFont(new Font("Times New Roman", Font.BOLD, 14));
		toolBarEditor.add(btnAnnulla);
	}
}
