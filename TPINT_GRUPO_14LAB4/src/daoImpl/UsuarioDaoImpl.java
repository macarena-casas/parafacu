package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dao.UsuarioDao;
import entidad.TipoUsuario;
import entidad.Usuario;

public class UsuarioDaoImpl implements UsuarioDao {

	private static final String update = "UPDATE usuarios SET nombre_usuario = ?, password = ?,tipo_usuario_id = ? WHERE usuario_id = ?";
	private static final String insert = "INSERT INTO usuarios(nombre_usuario, password, tipo_usuario_id) VALUES(?, ?, ?)";	
	private static final String list = "SELECT * FROM usuarios";
	private static final String get = "SELECT * FROM usuarios WHERE usuario_id = ?";
	private static final String queryExiste = "SELECT COUNT(*) FROM usuarios WHERE estado = 1 AND nombre_usuario = ?";
	private static final String queryBuscar = "SELECT usuario_id, nombre_usuario, password, tipo_usuario_id FROM usuarios WHERE estado = 1 AND nombre_usuario = ?";
	
	

	@Override
	public Usuario get(int usuario_id) {
	    Conexion conexion = new Conexion();
	    Usuario usuario = null;

	    try {
	        conexion.setearConsulta(get);
	        conexion.setearParametros(1, usuario_id);
	        ResultSet resultSet = conexion.ejecutarLectura();

	        if (resultSet.next()) {
	            TipoUsuarioDaoImpl tipoUsuarioDaoImpl = new TipoUsuarioDaoImpl();
	            String nombre_usuario = resultSet.getString("nombre_usuario");
	            String contraseña = resultSet.getString("password");
	            TipoUsuario tipousuario = tipoUsuarioDaoImpl.get(resultSet.getInt("tipo_usuario_id"));
	            int id_usuario = resultSet.getInt("usuario_id");
	            usuario = new Usuario(id_usuario, nombre_usuario, contraseña, tipousuario);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        conexion.cerrarConexion();
	    }
	    return usuario;
	}



	@Override
	public boolean insert(Usuario usuario_a_agregar) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public boolean update(Usuario usuario_a_modificar) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public ArrayList<Usuario> list() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Usuario buscarUsuario(String nombreUsuario) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public boolean existeNombreUsuario(String nombre) {
		// TODO Auto-generated method stub
		return false;
	}



}
