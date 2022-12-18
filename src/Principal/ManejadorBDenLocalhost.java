package Principal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class ManejadorBDenLocalhost {

	final String DB_URL = "jdbc:mysql://localhost/";
	final String USER = "root";
	final String PASS = "";

	Connection conexion;

	public void establecerConexion(String nombreBD) {
		try {
			conexion = DriverManager.getConnection(DB_URL.concat(nombreBD), USER, PASS);
		} catch (SQLException e) {
			System.out.println("No se puede establecer conexión con la BD: " + nombreBD);
		}
		System.out.println("Conexion establecida con la BD: " + nombreBD);
	}

	public void cerrarConexion(String nombreBD) {

		try {
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Conexión cerrada con la BD: " + nombreBD);
	}

	public void getEmpleados() throws SQLException {

		System.out.println("El listado:");
		Statement stmt = conexion.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM empleados");

		while (rs.next()) {
			System.out.print("ID: " + rs.getInt("id"));
			System.out.print(", Edad: " + rs.getInt("edad"));
			System.out.print(", Nombre: " + rs.getString("nombre"));
			System.out.println(", Apellidos: " + rs.getString("apellidos"));
		}
	}

	public void getEmpleadosEdad(String edad) throws SQLException {

		System.out.println("El listado de empleados con la edad " + edad + ":");
		Statement stmt = conexion.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM empleados WHERE edad=".concat(edad));

		while (rs.next()) {
			System.out.print("ID: " + rs.getInt("id"));
			System.out.print(", Edad: " + rs.getInt("edad"));
			System.out.print(", Nombre: " + rs.getString("nombre"));
			System.out.println(", Apellidos: " + rs.getString("apellidos"));
		}
	}

	public void crearTablaEmpleados() throws SQLException {

		Statement stmt = conexion.createStatement();

		String sql = "CREATE TABLE EMPLEADOS " + "(id INTEGER not NULL AUTO_INCREMENT, " + " nombre VARCHAR(255), "
				+ " apellidos VARCHAR(255), " + " edad INTEGER, " + " PRIMARY KEY ( id ))";

		stmt.executeUpdate(sql);
		System.out.println("Tabla empleados creada con las siguientes columnas:");
		System.out.println("empleados:[id, nombre, apellidos, edad] y clave principal: id");

	}

	public void borrarTablaEmpleados() throws SQLException {

		Statement stmt = conexion.createStatement();

		String sql = "DROP TABLE EMPLEADOS";

		stmt.executeUpdate(sql);
		System.out.println("Tabla borrada");

	}

	public void insertarDatosEmpleados(int numNuevosEmpleados) throws SQLException {

		for (int i = 0; i < numNuevosEmpleados; i++) {
			String sql = "INSERT INTO EMPLEADOS (nombre, apellidos, edad) VALUES (?,?,?)";
			PreparedStatement preparedStatement = conexion.prepareStatement(sql);
			preparedStatement.setString(1, cogerNombreAleatorio());
			preparedStatement.setString(2, cogerApellidosAleatorio());
			preparedStatement.setInt(3, ((new Random().nextInt(60))));
			preparedStatement.executeUpdate();

		}

		System.out.println("100 Datos aleadorios insertados en tabla empleados");

	}

	public int getNumItems(String tabla) throws SQLException {
		int num = 0;
		Statement stmt = conexion.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM ".concat(tabla));
		while (rs.next()) {
			num++;
		}
		return num;
	}

	private String cogerNombreAleatorio() {
		String nombres[] = { "Antonio", "Manuel", "José", "Francisco", "David", "Juan", "Javier", "José Antonio",
				"Daniel", "José Luis", "María Carmen", "María", "Carmen", "Ana María", "Josefa", "María Pilar",
				"Isabel", "Laura", "María Dolores", "María Teresa" };

		Random r = new Random();

		return nombres[r.nextInt(nombres.length)];

	}

	private String cogerApellidosAleatorio() {
		String apellidos[] = { "González", "Rodríguez", "Fernández", "Díaz", "Pérez", "Gómez", "Lucero", "Sosa",
				"Quiroga", "Martínez", "López", "García", "Muñoz", "Sánchez", "Flores", "Romero", "Cruz", "Benítez",
				"Ramírez", "Silva", "Ruiz" };
		Random r = new Random();

		return apellidos[r.nextInt(apellidos.length)] + " " + apellidos[r.nextInt(apellidos.length)];
	}
}
