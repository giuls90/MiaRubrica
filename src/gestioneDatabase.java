

import java.sql.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;
/**
 * Classe che gestisce la persistenza dell'applicazione MiaRubrica.
 * @author Giulia Santini
 *
 */
public class gestioneDatabase {

	// Nome del driver JDBC
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  


	// Credenziali del DB
	private static String USER;
	private static String PASS;
	private static String DB_URL;

	/**
	 * Metodo che si occupa di caricare il contenuto della tabella Contatti del database nell'arraylist ElencoContatti.
	 * @param miaRubrica Oggetto di tipo Rubrica
	 */
	public static void leggiRubricaDaDB (Rubrica miaRubrica) {
		Connection conn = null;
		Statement stmt = null;
		try{
			//Registrazione driver JDBC
			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection(DB_URL,USER,PASS);

			// Esecuzione della query
			stmt = conn.createStatement();

			String sql;
			sql = "SELECT nome, cognome, indirizzo, telefono, eta FROM Contatti";
			ResultSet rs = stmt.executeQuery(sql);

			//Estrazione dei risultati della query
			while(rs.next()){
				//Retrieve by column name
				String nome = rs.getString("nome");
				String cognome = rs.getString("cognome");
				String indirizzo = rs.getString("indirizzo");
				String telefono = rs.getString("telefono");
				int eta = rs.getInt("eta");
				miaRubrica.creaContatto(nome, cognome, indirizzo, telefono, eta);

			}

			rs.close();
			stmt.close();
			conn.close();
		}catch(SQLException se){

			se.printStackTrace();
		}catch(Exception e){

			e.printStackTrace();
		}finally{

			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
			}
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
		}

	}


	/**
	 * Metodo che si occupa di caricare il contenuto della tabella utenti del database nell'arraylist lista Utenti.
	 * @param listaUtenti Arraylist
	 */
	public static void leggiUtentiDaDB (ArrayList <Utente> listaUtenti) {
		Connection conn = null;
		Statement stmt = null;

		try{
			//Registrazione driver JDBC
			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection(DB_URL,USER,PASS);

			// Esecuzione della query
			stmt = conn.createStatement();

			String sql;
			sql = "SELECT username, password FROM utenti";
			ResultSet rs = stmt.executeQuery(sql);

			//Estrazione dei risultati della query
			while(rs.next()){
				//Retrieve by column name
				String user = rs.getString("username");
				String pwd = rs.getString("password");

				Utente u = new Utente (user,pwd);
				listaUtenti.add(u);

			}

			rs.close();
			stmt.close();
			conn.close();

		}catch(SQLException se){

			se.printStackTrace();
		}catch(Exception e){

			e.printStackTrace();
		}finally{

			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
			}
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
				se.printStackTrace();

			}
		}

	}




	/**
	 * Metodo che si occupa di aggiungere un contatto alla tabella Contatti del DB.
	 * @param n Nome del contatto da aggiungere
	 * @param c Cognome del contatto da aggiungere
	 * @param i Indirizzo del contatto da aggiungere
	 * @param t Telefono del contatto da aggiungere
	 * @param et Età del contatto da aggiungere
	 */
	public static void aggiungiContattoDB (String n, String c, String i , String t, int et) {

		Connection conn = null;
		Statement stmt = null;
		try{
			//Registrazione driver JDBC
			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection(DB_URL,USER,PASS);

			// Esecuzione della query
			stmt = conn.createStatement();

			String sql;
			sql = "INSERT INTO contatti (nome,cognome,indirizzo,telefono,eta)VALUES('"+n+"','"+c+"','"+i+"','"+t+"','"+et+"');";
			int success=stmt.executeUpdate(sql);

			System.out.println(success);


			stmt.close();
			conn.close();
		}catch(SQLException se){

			se.printStackTrace();
		}catch(Exception e){

			e.printStackTrace();
		}finally{

			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
			}
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
		}

	}



	/**
	 * Metodo che si occupa di modificare un determinato contatto nella tabella Contatti del DB.
	 * @param n Nuovo nome	
	 * @param c Nuovo cognome
	 * @param i Nuovo indirizzo
	 * @param t Nuovo telefono
	 * @param eta Nuova età
	 * @param oldn Vecchio nome
	 * @param oldc Vecchio cognome
	 */
	public static void modificaContattoDB (String n, String c, String i, String t, int eta, String oldn, String oldc) {

		Connection conn = null;
		Statement stmt = null;
		try{
			//Registrazione driver JDBC
			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection(DB_URL,USER,PASS);

			// Esecuzione della query
			stmt = conn.createStatement();

			String sql;
			sql = "UPDATE contatti SET 	nome = '"+n+"',cognome = '"+c+"',indirizzo = '"+i+"',telefono = '"+t+"',eta = '"+eta+"' WHERE nome = '"+oldn+"' AND cognome = '"+oldc+"';";
			int success=stmt.executeUpdate(sql);

			System.out.println(success);


			stmt.close();
			conn.close();
		}catch(SQLException se){

			se.printStackTrace();
		}catch(Exception e){

			e.printStackTrace();
		}finally{

			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
			}
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
		}

	}


	/**
	 * Metodo che si occupa di eliminare un determinato contatto.
	 * @param n Nome del contatto che si vuole eliminare
	 * @param c Cognome del contatto che si vuole eliminare
	 */
	public static void eliminaContattoDB (String n, String c) {

		Connection conn = null;
		Statement stmt = null;
		try{
			//Registrazione driver JDBC
			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection(DB_URL,USER,PASS);

			// Esecuzione della query
			stmt = conn.createStatement();

			String sql;
			sql = "DELETE FROM contatti WHERE nome = '"+n+"' and cognome = '"+c+"';";
			int success=stmt.executeUpdate(sql);

			System.out.println(success);


			stmt.close();
			conn.close();
		}catch(SQLException se){

			se.printStackTrace();
		}catch(Exception e){

			e.printStackTrace();
		}finally{

			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
			}
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
		}

	}

	/**
	 * Metodo che setta il valore dello username del database server
	 * @param uSER
	 */
	public static void setUSER(String uSER) {
		USER = uSER;
	}


	/**
	 * Metodo che setta il valore della password del database server
	 * @param pASS
	 */
	public static void setPASS(String pASS) {
		PASS = pASS;
	}


	/**
	 * Metodo che setta l'IP e il nome del database a cui collegarsi
	 * @param dB_URL
	 */
	public static void setDB_URL(String dB_URL) {
		DB_URL = dB_URL;
	}



}

