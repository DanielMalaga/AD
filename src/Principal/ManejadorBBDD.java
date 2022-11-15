package Principal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class ManejadorBBDD {

	final String DB_URL = "jdbc:mysql://localhost/clase";
	final String USER = "root";
	final String PASS = "";

	Connection con;

	public void establecerConexion() {
		try {
			con = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("No se puede establecer conexión");
		}

	}
	
	public void cerrarConexion() {
		
		
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void getEmpleados() throws SQLException {
		
			 System.out.println("El listado:");
	         Statement stmt = con.createStatement();
	         ResultSet rs = stmt.executeQuery("SELECT * FROM empleados");
	      
	         while (rs.next()) {
	            System.out.print("ID: " + rs.getInt("id"));
	            System.out.print(", Edad: " + rs.getInt("edad"));
	            System.out.print(", Nombre: " + rs.getString("nombre"));
	            System.out.println(", Apellidos: " + rs.getString("apellidos"));
	         }
    }

	public void crearTablaEmpleados() throws SQLException {

		Statement stmt = con.createStatement();

		String sql = "CREATE TABLE EMPLEADOS " + "(id INTEGER not NULL AUTO_INCREMENT, " + " nombre VARCHAR(255), "
				+ " apellidos VARCHAR(255), " + " edad INTEGER, " + " PRIMARY KEY ( id ))";

		stmt.executeUpdate(sql);
		System.out.println("Tabla creada");

	}
	
	public void borrarTablaEmpleados() throws SQLException {

		Statement stmt = con.createStatement();

		String sql = "DROP TABLE EMPLEADOS";

		stmt.executeUpdate(sql);
		System.out.println("Tabla borrada");

	}
	
	
	public void insertarDatosEmpleados() throws SQLException {
		
		
		
		for (int i = 0; i < 100; i++) {
			String sql = "INSERT INTO EMPLEADOS (nombre, apellidos, edad) VALUES (?,?,?)";
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1,cogerNombreAleatorio());
			preparedStatement.setString(2,cogerApellidosAleatorio());
			preparedStatement.setInt(3,((new Random().nextInt(60))));
			preparedStatement.executeUpdate();

		}
		
		System.out.println("Datos insertados");
		
	}
	
	
	private String cogerNombreAleatorio() {
		String nombres[]= {"Antonio","Manuel","José","Francisco","David","Juan","Javier","José Antonio","Daniel","José Luis","María Carmen","María","Carmen","Ana María","Josefa","María Pilar","Isabel","Laura","María Dolores","María Teresa"};
		
		Random r=new Random();
		
		
		
		return nombres[r.nextInt(nombres.length)];
		
	}
	
	private String cogerApellidosAleatorio() {
		String apellidos[]= {"González","Rodríguez","Fernández","Díaz","Pérez","Gómez","Lucero","Sosa","Quiroga","Martínez","López","García","Muñoz","Sánchez","Flores","Romero","Cruz","Benítez","Ramírez","Silva","Ruiz"};
		Random r=new Random();
		
		return apellidos[r.nextInt(apellidos.length)]+" "+apellidos[r.nextInt(apellidos.length)];
	}
}
