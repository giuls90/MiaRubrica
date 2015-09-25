

import java.sql.*;
public class gestioneDatabase {

	// Nome del driver JDBC e URL del database
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/rubrica";

	// Credenziali del DB
	static final String USER = "root";
	static final String PASS = "marley";



	public static void leggiDaDB (Rubrica miaRubrica) {
		Connection conn = null;
		Statement stmt = null;
		try{
			//STEP 2: Register JDBC driver
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

				//				//Display values
				//				System.out.print("nome: " + nome);
				//				System.out.print(", cognome: " + cognome);
				//				System.out.print(", indirizzo: " + indirizzo);
				//				System.out.print(", telefono: " + telefono);
				//				System.out.print(", eta:" + eta );
				//				System.out.println("");
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
}

