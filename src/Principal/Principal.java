package Principal;

import java.sql.SQLException;

public class Principal {

	public static void main(String[] args) {
		ManejadorBBDD mysql = new ManejadorBBDD();

		try {
			mysql.establecerConexion();
			mysql.borrarTablaEmpleados();
			mysql.crearTablaEmpleados();
			mysql.insertarDatosEmpleados();
			mysql.getEmpleados();
			mysql.cerrarConexion();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}
}
