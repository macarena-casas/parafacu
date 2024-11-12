package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.TipoUsuarioDao;
import entidad.TipoUsuario;

public class TipoUsuarioDaoImpl implements TipoUsuarioDao {

    private static final String get = "SELECT * FROM tipos_usuarios WHERE tipos_usuario_id = ?";

    public TipoUsuario get(int idtipousuario) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        PreparedStatement statement;
        Connection conexion = Conexion.getConexion().getSQLConexion();
        
        try {
            statement = conexion.prepareStatement(get);
            statement.setInt(1, idtipousuario);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                TipoUsuario tipoUsuario = new TipoUsuario(
                    resultSet.getInt("tipos_usuario_id"),
                    resultSet.getString("tipo_usuario")
                );
                return tipoUsuario;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
}
}