package Principal;

import java.sql.SQLException;
import java.util.Random;

public class Principal {

	public static void main(String[] args) {
		ManejadorBDenLocalhost mysql = new ManejadorBDenLocalhost();

		try {
			mysql.establecerConexion("clase");
			// ya no hace falta borrar, crear ni insertar.
			// mysql.borrarTablaEmpleados();
			// mysql.crearTablaEmpleados();
			// mysql.insertarDatosEmpleados(100);
			// mysql.getEmpleados();
			Random r = new Random();
			mysql.getEmpleadosEdad(String.valueOf(r.nextInt(60)));
			System.out.println("El numero de empleados ahora es de " + mysql.getNumItems("empleados"));
			mysql.insertarDatosEmpleados(100);
			System.out.println("El numero de empleados ahora es de " + mysql.getNumItems("empleados"));

			mysql.cerrarConexion("clase");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
