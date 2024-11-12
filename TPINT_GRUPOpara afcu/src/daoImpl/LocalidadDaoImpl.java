package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import dao.LocalidadDao;
import entidad.Localidad;
import entidad.Provincia;

public class LocalidadDaoImpl implements LocalidadDao {
	private static final String list = "SELECT * FROM localidades";
	private static final String get = "SELECT * FROM localidades WHERE localidad_id = ?";
	
	@Override
	public Localidad get(int localidad_id) {
		try 
    	{
    		Class.forName("com.mysql.jdbc.Driver");
    	}catch (ClassNotFoundException e){
    		e.printStackTrace();
    	}
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		
		try {
			statement = conexion.prepareStatement(get);
			statement.setInt(1, localidad_id);
			ResultSet result_set = statement.executeQuery();
			while(result_set.next()) {
				int localidadId = result_set.getInt("localidad_id");
				String nombre = result_set.getString("nombre");
				int provincia_id = result_set.getInt("provincia_id");
				
				ProvinciaDaoImpl provinciaDaoImpl = new ProvinciaDaoImpl();
				Provincia provincia = provinciaDaoImpl.get(provincia_id);
				
				Localidad localidad = new Localidad(localidadId,nombre,provincia);
				return localidad;
			}
		}		
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public ArrayList<Localidad> list() {
		try 
    	{
    		Class.forName("com.mysql.jdbc.Driver");
    	}catch (ClassNotFoundException e){
    		e.printStackTrace();
    	}
		ArrayList<Localidad> list_localidades = new ArrayList<Localidad>();
		try {
			Connection conexion = Conexion.getConexion().getSQLConexion();
			Statement statement = conexion.createStatement();
			ResultSet result_set = statement.executeQuery(list);
			while(result_set.next()) {
				
				int localidadId = result_set.getInt("localidad_id");
				String nombre = result_set.getString("nombre");
				int provincia_id = result_set.getInt("provincia_id");
				
				ProvinciaDaoImpl provinciaDaoImpl = new ProvinciaDaoImpl();
				Provincia provincia = provinciaDaoImpl.get(provincia_id);
				
				Localidad localidad = new Localidad(localidadId,nombre,provincia);

				list_localidades.add(localidad);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return list_localidades;
	}

}