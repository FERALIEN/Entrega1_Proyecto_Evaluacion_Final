package ExtraerDatosApi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Conector {
	
	//Metodo para iniciar la conexion
	private static Connection iniciarConexion() {
		 try {
			 Class.forName("com.mysql.cj.jdbc.Driver");
			 Connection con = DriverManager.getConnection("jdbc:mysql://viaduct.proxy.rlwy.net:21832/railway", "root", "C54GGd4ee-BCCag1bB2E5G-AG44FGCbd");
			 System.out.println("Conexión a la BD");
			 return con;
			 }
		 catch (Exception e) {
		 System.out.println("Error en conexión");
		 }
		return null;
	}
	
	//Metodo intermedio
	public static void ejecutarConexion(ArrayList<Nave> listaNaves) {
		Connection con = iniciarConexion();
		
		try {
			Statement st = con.createStatement();
			//Si no existe la tabla se crea
			st.execute("CREATE TABLE IF NOT EXISTS naves (nombre varchar(100) NOT NULL, modelo varchar(100) NOT NULL, fabricante varchar(100) NOT NULL, longitud varchar(100) NOT NULL, tripulacion varchar(100) NOT NULL, coste varchar(100) NOT NULL, pasajeros varchar(100) NOT NULL, carga varchar(100) NOT NULL, clase varchar(100) NOT NULL)");
			//Recorre la lista de naves y las introduce en la base de datos
			for (Nave nave : listaNaves) {
			st.execute("INSERT INTO naves (nombre, modelo, fabricante, longitud, tripulacion, coste, pasajeros, carga, clase) values " + 
					"('"+nave.getName()+"','"+nave.getModel()+"','"+nave.getManufacturer()+"','"+nave.getLength()+"','"+nave.getCrew()+"','"+nave.getCost_in_credits()+"','"+nave.getPassengers()+"','"+nave.getCargo_capacity()+"','"+nave.getStarship_class()+"');");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		cerrarConexion(con);
	}

	
	//Metodo para cerrar la conexion
	private static void cerrarConexion(Connection con) {
		try {
			 con.close();
			 System.out.println("Conexión cerrada");
			 }
		catch (SQLException e) {
		System.out.println("Error al cerrar conexión");
		} 
	}
}
