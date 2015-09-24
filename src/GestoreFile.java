import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe che si occupa della lettura, scrittura e salvataggio su file della Rubrica.
 * @author Giulia Santini
 * @version 2.1
 *
 */
public class GestoreFile {





	private final static String PATH = "informazioni/";



	/**
	 * Metodo che si occupa di scrivere su un file, chiamato "informazioni.text" l'elenco dei contatti della rubrica.
	 * @param miaRubrica Oggetto di tipo Rubrica.
	 * @throws FileNotFoundException
	 */
	protected static void scriviSuFile1(Rubrica miaRubrica)
			throws FileNotFoundException {

		PrintStream out = new PrintStream(new FileOutputStream(
				"./informazioni.txt", false));
		ArrayList<Persona> elencoContatti = miaRubrica.getElencoContatti();
		for (Persona p : elencoContatti) {
			out.print(p.getNome() + ";" + p.getCognome() + ";" + p.getIndirizzo() + ";"
					+ p.getTelefono() + ";" + p.getEta() + "\n");
		}

		out.close();
	}

	/**
	 * Metodo che si occupa di caricare il contenuto informativo del file "informazioni.txt", all'avvio dell'applicazione.
	 * @param miaRubrica Oggetto di tipo Rubrica.
	 * @throws FileNotFoundException
	 */
	protected static void leggiDaFile1(Rubrica miaRubrica)
			throws FileNotFoundException {
		Scanner s1 = null;

		try {
			s1 = new Scanner(new BufferedReader(new FileReader(
					"informazioni.txt")));
			s1.useDelimiter("\\s*;*;*;*;*\n\\s*");

			while (s1.hasNext()) {
				String riga = s1.next();
				Scanner scanner = new Scanner(riga);
				Scanner s2 = scanner.useDelimiter("\\s*;\\s*");

				while (s2.hasNext()) 
					miaRubrica.creaContatto(s2.next(), s2.next(), s2.next(), s2.next(), Integer.valueOf(s2.next()) );

				scanner.close();
			}
		} catch (java.io.IOException e) {
			e.printStackTrace();
		} finally {
			if (s1 != null) {
				s1.close();
			}
		}
	}

	/**
	 * Metodo che si occupa di caricare, all'avvio dell'applicazione, tutto il contenuto informativo presente nella cartella, indicata dalla 
	 * variabile PATH.
	 * @param miaRubrica Oggetto di tipo Rubrica
	 * @throws FileNotFoundException
	 */
	protected static void leggiDaFile2(Rubrica miaRubrica)
			throws FileNotFoundException {

		Scanner s1 = null;

		File directory = new File(PATH);



		if (!directory.exists()) {

			try {
				directory.mkdir();
			} catch (SecurityException se) {
				// handle it
			}

		}


		for (File file : directory.listFiles()) 
			if (file.isFile())  {
				try {

					s1 = new Scanner(new BufferedReader(new FileReader(file)));

					String riga = s1.next();
					Scanner scanner = new Scanner(riga);
					Scanner s2 = scanner.useDelimiter("\\s*;\\s*");

					while (s2.hasNext())
						miaRubrica.creaContatto(s2.next(),s2.next(),s2.next(),s2.next(),Integer.valueOf(s2.next()));
					scanner.close();
				} 	catch (java.io.IOException e) {
					e.printStackTrace();
				} finally {
					if (s1 != null) 
						s1.close();

				}

			}

	}

	/**
	 * Metodo che si occupa di eliminare un determinato contatto dalla cartella indicata dalla variabile PATH.
	 * @param nome Nome dell'oggetto Persona che si vuole eliminare.
	 * @param cognome Cognome dell'oggetto Persona che si vuole eliminare.
	 * @throws FileNotFoundException
	 */
	protected static void eliminaFile(String nome, String cognome)
			throws FileNotFoundException {

		String fileName = PATH + nome + "-" + cognome +".txt";
		File f = new File(fileName);
		System.out.println("Path assoluto:"+f.getAbsolutePath());
		boolean success =f.delete();
		System.out.println(success);
	}

	/**
	 * Metodo che si occupa di aggiungere un determinato contatto alla cartella indicata dalla variabile PATH.
	 * @param nome Nome della Persona che si vuole eliminare.
	 * @param cognome Cognome della Persona che si vuole eliminare.
	 * @param indirizzo Indirizzo della Persona che si vuole eliminare.
	 * @param telefono Telefono della Persona che si vuole eliminare.
	 * @param eta Età della Persona che si vuole eliminare.
	 * @throws FileNotFoundException
	 */
	protected static void aggiungiFile(String nome, String cognome, String indirizzo, String telefono, int eta)
			throws FileNotFoundException{

		PrintStream out1 = new PrintStream(new FileOutputStream(PATH +nome + "-" + cognome + ".txt"));
		out1.print( nome + ";" + cognome + ";" + indirizzo + ";"+ telefono + ";"  + String.valueOf(eta) +"\n");
		out1.close();

	}

}
