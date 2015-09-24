
/**
 * Classe che definisce una Persona.
 * @author Giulia Santini
 * @version 2.1
 *
 */
public class Persona {
	private String nome;
	private String cognome;
	private String indirizzo;
	private String telefono;
	private int eta;


	/**
	 * Costruttore della classe Persona.
	 * @param nome Nome dell'oggetto Persona	
	 * @param cognome Cognome dell'oggetto Persona
	 * @param indirizzo Indirizzo dell'oggetto Persona
	 * @param telefono Telefono dell'oggetto Persona
	 * @param eta Eta dell'oggetto Persona
	 */
	public Persona (String nome, String cognome, String indirizzo, String telefono, int eta)
	{
		this.setNome(nome);
		this.setCognome(cognome);
		this.setIndirizzo(indirizzo);
		this.setTelefono(telefono);
		this.setEta(eta);

	}

	/**
	 * Metodo che restituisce il nome di un oggetto di tipo Persona.
	 * @return nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Metodo che setta il valore del nome di un oggetto di tipo Persona.
	 * @param nome Nome dell'oggetto Persona
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Metodo che restituisce il cognome di un oggetto di tipo Persona.
	 * @return cognome
	 */
	public String getCognome() {
		return cognome;
	}

	/**
	 * Metodo che setta il valore del cognome di un oggetto di tipo Persona.
	 * @param cognome Cognome dell'oggetto Persona
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	/**
	 * Metodo che restituisce l'indirizzo di un oggetto di tipo Persona.
	 * @return indirizzo
	 */
	public String getIndirizzo() {
		return indirizzo;
	}

	/**
	 * Metodo che setta il valore dell'indirizzo di un oggetto di tipo Persona.
	 * @param indirizzo Indirizzo dell'oggetto Persona
	 */
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	/**
	 * Metodo che restituisce il telefono di un oggetto di tipo Persona.
	 * @return telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * Metodo che setta il valore del telefono di un oggetto di tipo Persona.
	 * @param telefono Telefono di Persona
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * Metodo che restituisce l'età di un oggetto di tipo persona.
	 * @return eta 
	 */
	public int getEta() {
		return eta;
	}

	/**
	 * Metodo che setta il valore dell'età di un oggetto di tipo Persona.
	 * @param eta di Persona
	 */
	public void setEta(int eta) {
		this.eta = eta;
	}
}
