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
	public boolean insert(Usuario usuario) {
		Conexion cone = new Conexion();
		    boolean isInsertExitoso = false;
		    try
		    {
		    	cone.setearConsulta(insert);
		    	cone.setearParametros(1, usuario.getNombreusuario());
		        cone.setearParametros(2,usuario.getContraseña());
		        cone.setearParametros(3,usuario.getTipoUsuario().getIdTipousuario());     
		        if(cone.ejecutarAccion() > 0)
		        {
		            cone.commit();
		            isInsertExitoso = true;
		        }
		    
	} catch (SQLException e) {
		e.printStackTrace();
		cone.rollback();
	}
		    
	 finally {
		cone.cerrarConexion();
	}

		    return isInsertExitoso;
	}

	@Override
	public boolean update(Usuario usuario) {
		Conexion cone = new Conexion();

	        boolean isUpdateExitoso = false;
	        try {
	        	cone.setearConsulta(update);
	        	cone.setearParametros(1, usuario.getNombreusuario());
	        	cone.setearParametros(2,usuario.getContraseña());
	        	cone.setearParametros(3,usuario.getTipoUsuario().getIdTipousuario());
	        	cone.setearParametros(4,usuario.getIdusuario());
	            
	            if (cone.ejecutarAccion() > 0) {
	                cone.commit();
	                isUpdateExitoso = true;
	            }
	        } catch (SQLException e) {
				e.printStackTrace();
				cone.rollback();
			
	        } finally {
	    		cone.cerrarConexion();
	    	}

	        return isUpdateExitoso;
	}


	@Override
	public ArrayList<Usuario> list() {
		Conexion cone = new Conexion();

		ArrayList<Usuario> list_usuarios = new ArrayList<Usuario>();
		try {
			cone.setearConsulta(list);
			ResultSet result_set = cone.ejecutarLectura();

			while(result_set.next()) {
				
				String nombre_usuario = result_set.getString("nombre_usuario");
				String password = result_set.getString("password");
				TipoUsuarioDaoImpl tipoUsuarioDaoImpl = new TipoUsuarioDaoImpl();
				TipoUsuario tipo_usuario = tipoUsuarioDaoImpl.get(result_set.getInt("tipo_usuario_id"));
				int id_usuario = result_set.getInt("usuario_id");
				Usuario usuario = new Usuario(id_usuario,nombre_usuario,password,tipo_usuario);

				list_usuarios.add(usuario);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		} finally {
			cone.cerrarConexion();
		}

		
		return list_usuarios;
	}
	
	@Override
    public boolean existeNombreUsuario(String nombre) {
		Conexion cone = new Conexion();

        boolean existe = false;
        
        try {
        	cone.setearConsulta(queryExiste);
        	cone.setearParametros(1, nombre);
            ResultSet resultSet = cone.ejecutarLectura();
            
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                if (count > 0) {
                    existe = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
    		cone.cerrarConexion();
    	}

        return existe;
    }
	
	@Override
    public Usuario buscarUsuario(String nombreUsuario) {
		Conexion cone = new Conexion();
        Usuario usuario = null;

        try {
        	cone.setearConsulta(queryBuscar);
            cone.setearParametros(1, nombreUsuario);
            ResultSet resultSet = cone.ejecutarLectura();

            if (resultSet.next()) {
            	int id = resultSet.getInt("usuario_id");
                String nombre = resultSet.getString("nombre_usuario");
                String password = resultSet.getString("password");
				TipoUsuarioDaoImpl tipoUsuarioDaoImpl = new TipoUsuarioDaoImpl();
				TipoUsuario tipoUsuario = tipoUsuarioDaoImpl.get(resultSet.getInt("tipo_usuario_id"));

                usuario = new Usuario();
                usuario.setIdusuario(id);
                usuario.setNombreusuario(nombre);
                usuario.setContraseña(password);
                usuario.setTipoUsuario(tipoUsuario);
            } 

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
    		cone.cerrarConexion();
    	}


        return usuario;
        
    }



}
