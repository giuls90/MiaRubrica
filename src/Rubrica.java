

import java.util.ArrayList;


/**
 * Classe che definisce una Rubrica e i metodi per la gestione di essa.
 * @author Giulia Santini
 * @version 2.1
 *
 */
public class Rubrica {




	/**
	 * Arraylist che conterrà tutti i contatti della Rubrica.
	 */
	ArrayList <Persona> elencoContatti = new ArrayList <Persona>();



	/**
	 * Metodo che crea un oggetto Persona e lo aggiunge all'elenco dei contatti, implementato da un'arraylist.
	 * @param nome Nome dell'oggetto Persona
	 * @param cognome Cognome dell'oggetto Persona
	 * @param indirizzo Indirizzo dell'oggetto Persona
	 * @param telefono Telefono dell'oggetto Persona
	 * @param eta Età dell'oggetto Persona
	 */
	public void creaContatto (String nome, String cognome, String indirizzo, String telefono, int eta)
	{
		Persona p= new Persona (nome, cognome, indirizzo, telefono, eta);
		elencoContatti.add(p);
	}	

	/**
	 * Metodo che elimina un oggetto Persona dall'elenco dei contatti.
	 * @param index Indice dell'elemento dell'arraylist che si vuole eliminare
	 */
	public void eliminaContatto (int index)
	{
		elencoContatti.remove(index);

	}

	/**
	 * Metodo che modifica una oggetto Persona dell'elenco dei contatti.
	 * @param index Indice dell'oggetto Persona che si vuole modificare
	 * @param nome Nuovo nome dell'oggetto Persona che si vuole modificare
	 * @param cognome Nuovo cognome dell'oggetto Persona che si vuole modificare
	 * @param indirizzo Nuovo indirizzo dell'oggetto Persona che si vuole modificare
	 * @param telefono Nuovo telefono dell'oggetto Persona che si vuole modificare
	 * @param eta Nuova età dell'oggetto Persona che si vuole modificare
	 */
	public void modificaContatto (int index, String nome, String cognome, String indirizzo, String telefono, int eta)
	{

		Persona p = elencoContatti.get(index);
		p.setNome(nome);
		p.setCognome(cognome);
		p.setIndirizzo(indirizzo);
		p.setTelefono(telefono);
		p.setEta(eta);
	}


	/**
	 * Metodo che restituisce l'elenco dei Contatti.
	 * @return elencoContatti  
	 */
	public ArrayList<Persona> getElencoContatti ()
	{
		return elencoContatti;
	}

}



