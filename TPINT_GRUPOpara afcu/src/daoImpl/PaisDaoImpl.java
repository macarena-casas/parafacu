package daoImpl;

import java.sql.ResultSet;
import java.util.ArrayList;

import dao.PaisDao;
import entidad.Pais;

public class PaisDaoImpl implements PaisDao {
	private static final String list = "SELECT * FROM paises";
	private static final String get = "SELECT * FROM paises WHERE pais_id = ?";

	@Override
	public Pais get(int idPais) {
		Conexion conexion = new Conexion();
		Pais pais = null;

		try {
			conexion.setearConsulta(get);
			conexion.setearParametros(1, idPais);
			ResultSet resultSet = conexion.ejecutarLectura();

			if (resultSet.next()) {
				String nombrePais = resultSet.getString("nombre");
				int paisId = resultSet.getInt("pais_id");
				pais = new Pais(paisId, nombrePais);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conexion.cerrarConexion();
		}

		return pais;
	}

	@Override
	public ArrayList<Pais> list() {
		// TODO Auto-generated method stub
		return null;
	}
}
