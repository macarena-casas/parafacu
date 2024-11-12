package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import dao.ProvinciaDao;
import entidad.Pais;
import entidad.Provincia;

public class ProvinciaDaoImpl implements ProvinciaDao {


	private static final String list = "SELECT * FROM provincias";
	private static final String get = "SELECT * FROM provincias WHERE provincia_id = ?";
	

	@Override
	public Provincia get(int prov_id) {
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
			statement.setInt(1, prov_id);
			ResultSet result_set = statement.executeQuery();
			while(result_set.next()) {
				
				String nombre_provincia = result_set.getString("nombre");
				int pais_id = result_set.getInt("pais_id");
				int provincia_id = result_set.getInt("provincia_id");
				PaisDaoImpl paisDaoImpl = new PaisDaoImpl();
				Pais pais = paisDaoImpl.get(pais_id);
		
				Provincia provincia = new Provincia(provincia_id,nombre_provincia,pais);
				return provincia;
			}
		}		
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

	@Override
	public ArrayList<Provincia> list() {
		try 
    	{
    		Class.forName("com.mysql.jdbc.Driver");
    	}catch (ClassNotFoundException e){
    		e.printStackTrace();
    	}
		ArrayList<Provincia> list_provincias = new ArrayList<Provincia>();
		try {
			Connection conexion = Conexion.getConexion().getSQLConexion();
			Statement statement = conexion.createStatement();
			ResultSet result_set = statement.executeQuery(list);
			while(result_set.next()) {
				String nombre_provincia = result_set.getString("nombre");
				int pais_id = result_set.getInt("pais_id");
				int provincia_id = result_set.getInt("provincia_id");
				PaisDaoImpl paisDaoImpl = new PaisDaoImpl();
				Pais pais = paisDaoImpl.get(pais_id);
				Provincia provincia = new Provincia(provincia_id,nombre_provincia,pais);
				list_provincias.add(provincia);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return list_provincias;
	}
	
}
