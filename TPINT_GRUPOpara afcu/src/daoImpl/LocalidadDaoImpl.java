package daoImpl;

import java.sql.ResultSet;
import java.util.ArrayList;

import dao.LocalidadDao;
import entidad.Localidad;
import entidad.Provincia;

public class LocalidadDaoImpl implements LocalidadDao {
	private static final String list = "SELECT * FROM localidades";
	private static final String get = "SELECT * FROM localidades WHERE localidad_id = ?";

	@Override
	public Localidad get(int localidad_id) {
		Conexion conexion = new Conexion();
		Localidad localidad = null;

		try {
			conexion.setearConsulta(get);
			conexion.setearParametros(1, localidad_id);
			ResultSet resultSet = conexion.ejecutarLectura();

			if (resultSet.next()) {
				int localidadId = resultSet.getInt("localidad_id");
				String nombre = resultSet.getString("nombre");
				int provincia_id = resultSet.getInt("provincia_id");

				ProvinciaDaoImpl provinciaDaoImpl = new ProvinciaDaoImpl();
				Provincia provincia = provinciaDaoImpl.get(provincia_id);

				localidad = new Localidad(localidadId, nombre, provincia);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conexion.cerrarConexion();
		}

		return localidad;
	}

	@Override
	public ArrayList<Localidad> list() {
		Conexion conexion = new Conexion();
		ArrayList<Localidad> listLocalidades = new ArrayList<>();

		try {
			conexion.setearConsulta(list);
			ResultSet resultSet = conexion.ejecutarLectura();

			while (resultSet.next()) {
				int localidadId = resultSet.getInt("localidad_id");
				String nombre = resultSet.getString("nombre");
				int provincia_id = resultSet.getInt("provincia_id");

				ProvinciaDaoImpl provinciaDaoImpl = new ProvinciaDaoImpl();
				Provincia provincia = provinciaDaoImpl.get(provincia_id);

				Localidad localidad = new Localidad(localidadId, nombre, provincia);
				listLocalidades.add(localidad);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conexion.cerrarConexion();
		}

		return listLocalidades;
	}
}
