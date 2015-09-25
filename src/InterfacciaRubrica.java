

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JToolBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

/**
 * Classe che rappresenta l'interfaccia dell'applicazione Rubrica.
 * @author Giulia Santini
 * @version 2.1
 *
 */
public class InterfacciaRubrica extends JFrame {


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final JScrollPane scrollPaneTabellaContatti = new JScrollPane();
	private JTable tabellaContatti;
	private Rubrica miaRubrica;
	private InterfacciaRubrica home;


	/**
	 * Costruttore del  frame InterfacciaRubrica.
	 */
	public InterfacciaRubrica() {
		home=this;
		miaRubrica= new Rubrica();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panelTabellaContatti = new JPanel();
		panelTabellaContatti.setBounds(0, 0, 434, 219);
		contentPane.add(panelTabellaContatti);
		panelTabellaContatti.setLayout(null);
		scrollPaneTabellaContatti.setBounds(0, 0, 434, 219);
		panelTabellaContatti.add(scrollPaneTabellaContatti);

		//Creo la Jtable usando il metodo: public JTable(TableModel model)

		Object[] nomiColonne ={"Nome", "Cognome", "Telefono"};
		TableModel model = new DefaultTableModel(nomiColonne, 0);
		tabellaContatti = new JTable(model);


		scrollPaneTabellaContatti.setViewportView(tabellaContatti);

		JPanel panelBottoniHome = new JPanel();
		panelBottoniHome.setBounds(0, 220, 434, 41);
		contentPane.add(panelBottoniHome);
		panelBottoniHome.setLayout(null);

		JToolBar toolBarBottoniHome = new JToolBar();
		toolBarBottoniHome.setBounds(0, 0, 434, 41);
		panelBottoniHome.add(toolBarBottoniHome);

		JButton btnNuovo = new JButton("Nuovo");
		btnNuovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int flag=-1;
				EditorPersona editor = new EditorPersona(miaRubrica, home, flag);
				editor.setVisible(true);
			}
		});
		btnNuovo.setIcon(new ImageIcon("Immagini/aggiungi2.png"));
		btnNuovo.setForeground(Color.GREEN);
		btnNuovo.setFont(new Font("Times New Roman", Font.BOLD, 14));
		toolBarBottoniHome.add(btnNuovo);

		JButton btnModifica = new JButton("Modifica");
		btnModifica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int riga = tabellaContatti.getSelectedRow();

				//Controlla che sia stata selezionata una riga da modificare
				if (riga != -1) {

					int index = tabellaContatti.convertRowIndexToModel(riga);

					EditorPersona editor = new EditorPersona(miaRubrica,home,index);
					Persona p = miaRubrica.getElencoContatti().get(index);

					editor.getTextFieldNome().setText(p.getNome());
					editor.getTextFieldCognome().setText(p.getCognome());
					editor.getTextFieldIndirizzo().setText(p.getIndirizzo());
					editor.getTextFieldTelefono().setText(p.getTelefono());
					editor.getTextFieldEta().setText(String.valueOf(p.getEta()));
					editor.setVisible(true);
				} else {

					JOptionPane.showMessageDialog(null, "Selezionare il contatto su cui effettuare la modifica!", "Errore", JOptionPane.INFORMATION_MESSAGE);

				}
			}
		});
		btnModifica.setIcon(new ImageIcon("Immagini/modifica2.png"));
		btnModifica.setForeground(Color.BLUE);
		btnModifica.setFont(new Font("Times New Roman", Font.BOLD, 14));
		toolBarBottoniHome.add(btnModifica);

		JButton btnElimina = new JButton("Elimina");
		btnElimina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int riga = tabellaContatti.getSelectedRow();
				int i = tabellaContatti.convertRowIndexToModel(riga);

				//Se non è stata selezionata nessuna riga, genera un messaggio di errore.
				if (i==-1) 
					JOptionPane.showMessageDialog(null, "Selezionare il contatto che si vuole eliminare!", "Errore", JOptionPane.INFORMATION_MESSAGE);

				else {
					//Memorizza il contatto che si vuole eliminare
					Persona p = miaRubrica.getElencoContatti().get(i);

					String nome = p.getNome();
					String cognome = p.getCognome();

					//Chiede la conferma di eliminazione
					int conferma = JOptionPane.showConfirmDialog(null,"Eliminare: " + nome + " "+ cognome + " ?",
							"Richiesta di conferma : ", JOptionPane.YES_NO_OPTION);
					//Se è positiva
					if (conferma == JOptionPane.YES_OPTION) {

						//Elimina il file corrispondente alla persona, e l'oggetto Persona dell'arraylist
						gestioneDatabase.eliminaContattoDB(nome, cognome);
						miaRubrica.eliminaContatto(i);
						//Aggiorna la Jtable
						aggiornaTabella();
					} 
				}

			}
		});
		btnElimina.setIcon(new ImageIcon("Immagini/cancella2.png"));
		btnElimina.setForeground(Color.RED);
		btnElimina.setFont(new Font("Times New Roman", Font.BOLD, 14));
		toolBarBottoniHome.add(btnElimina);

		//Carica tutto il contenuto informativo dal DB

		//Aggiunge i contatti all'arraylist elencoContatti
		gestioneDatabase.leggiDaDB(miaRubrica);
		//Aggiorna la JTable, mostrando gli elementi dell'arraylist elencoContatti
		aggiornaTabella();

	}

	/**
	 * Metodo che si occupa di aggiornare la JTable, contenente l'elenco dei contatti della Rubrica.
	 */
	protected void aggiornaTabella()
	{

		//Reset tabella
		DefaultTableModel modello = (DefaultTableModel) tabellaContatti.getModel();
		modello.setRowCount(0);
		tabellaContatti.revalidate();

		//aggiornamento tabella

		ArrayList<Persona> elencoContatti = miaRubrica.getElencoContatti();
		for (Persona p: elencoContatti)
			modello.addRow(new Object[]{p.getNome(), p.getCognome(), p.getTelefono()});

		tabellaContatti.setModel(modello);

	}
}
