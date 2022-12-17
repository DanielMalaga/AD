package Principal;

import java.sql.SQLException;

public class Principal {

	public static void main(String[] args) {
		ManejadorBBDD mysql = new ManejadorBBDD();

		try {
			mysql.establecerConexion();
			// ya no hace falta borrar, crear ni insertar.
			// mysql.borrarTablaEmpleados();
			// mysql.crearTablaEmpleados();
			// mysql.insertarDatosEmpleados();
			// mysql.getEmpleados();
			mysql.getEmpleadosEdad("19");

			mysql.cerrarConexion();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
