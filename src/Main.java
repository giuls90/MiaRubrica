import java.awt.EventQueue;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Classe che setta i parametri di configurazione per la connessione al DB server e lancia l'applicazione miaRubrica
 * @author Giulia Santini
 *
 */
public class Main {



	public static void main(String[] args) {
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream("config.properties");

			// load a properties file
			prop.load(input);
			
			gestioneDatabase.setUSER(prop.getProperty("dbuser"));
			gestioneDatabase.setPASS(prop.getProperty("dbpassword"));
			
			//Genero la stringa jdbc per il nome e l'ip del database
			String porta=prop.getProperty("dbporta");
			String ip=prop.getProperty("dbIP");
			String dbURL="jdbc:mysql://"+ip+":"+porta+"/rubrica";
			
			gestioneDatabase.setDB_URL(dbURL);
			
			// get the property value and print it out
			System.out.println(dbURL);
			System.out.println(prop.getProperty("dbuser"));
			System.out.println(prop.getProperty("dbpassword"));

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}



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

}
