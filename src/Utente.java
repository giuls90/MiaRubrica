import java.util.ArrayList;

/**
 * Classe che definisce un utente.
 * @author Giulia Santini
 *
 */
public class Utente {

	private String username;
	private String password;

	
/**
 * Costruttore della classe Utente.
 * @param username Username dell'oggetto Utente.
 * @param password Password dell'oggetto Utente.
 */
public Utente (String username, String password){
	this.username=username;
	this.password=password;
	
}
	
	/**
	 * Metodo che restituisce lo username di un Utente.
	 * @return username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Metodo che restituisce la password di un Utente.
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

}

