package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dao.PaisDao;
import entidad.Pais;

public class PaisDaoImpl implements PaisDao {
	private static final String list = "SELECT * FROM paises";
	private static final String get = "SELECT * FROM paises WHERE pais_id = ?";

	@Override
	public Pais get(int idPais) {
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
			statement.setInt(1, idPais);
			ResultSet result_set = statement.executeQuery();
			while(result_set.next()) {
				String nombre_pais = result_set.getString("nombre");
				int paisId = result_set.getInt("pais_id");
				Pais pais = new Pais(paisId,nombre_pais);
				return pais;
			}
		}		
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<Pais> list() {
		// TODO Auto-generated method stub
		return null;
	}

}
