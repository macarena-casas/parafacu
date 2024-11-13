package daoImpl;

import java.sql.ResultSet;
import dao.TipoUsuarioDao;
import entidad.TipoUsuario;

public class TipoUsuarioDaoImpl implements TipoUsuarioDao {
    @Override
    public TipoUsuario get(int idTipoUsuario) {
        TipoUsuario tipoUsuario = null;
        Conexion conexion = new Conexion();
        
        try {
            
            conexion.setearConsulta("SELECT * FROM tipos_usuarios WHERE tipos_usuario_id = ?");
            conexion.setearParametros(1, idTipoUsuario);

           
            ResultSet resultSet = conexion.ejecutarLectura();

            
            if (resultSet.next()) {
                tipoUsuario = new TipoUsuario(
                    resultSet.getInt("tipos_usuario_id"),
                    resultSet.getString("tipo_usuario")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            
            conexion.cerrarConexion();
        }

        return tipoUsuario;
    }
}
