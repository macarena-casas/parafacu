package daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;




public class Conexion {
    private Connection conexion;
    private PreparedStatement comando;
    private ResultSet lector;
    private String url = "jdbc:mysql://localhost:3306/EmeraldBank_GRUPO14?useSSL=false&useUnicode=yes";
    private String user = "root"; 
    private String password = "root";
   //private String password = "";

    public Conexion() {
        try {  
            this.conexion = DriverManager.getConnection(url, user, password);
            this.conexion.setAutoCommit(false);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public ResultSet getLector() {
        return lector;
    }

    public void setearConsulta(String consulta) throws SQLException {
        if (conexion == null || conexion.isClosed()) {
            this.abrirConexion();  
        }
       
        this.comando = conexion.prepareStatement(consulta);
    }

    public ResultSet ejecutarLectura() throws SQLException {
        if (comando == null) {
            throw new SQLException("La consulta no ha sido inicializada.");
        }
        try {
            lector = comando.executeQuery();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
        return lector;
    }

    public void cerrarConexion() {
        try {
            if (lector != null && !lector.isClosed()) {
                lector.close();
            }
            if (comando != null && !comando.isClosed()) {
                comando.close();
            }
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void setearParametros(int index, Object obj) throws SQLException {
        if (comando == null) {
            throw new SQLException("El comando no ha sido inicializado.");
        }
        if (obj instanceof String) {
            comando.setString(index, (String) obj);
        } else if (obj instanceof Integer) {
            comando.setInt(index, (Integer) obj);
        } else if (obj instanceof Float) {
            comando.setFloat(index, (Float) obj);
        } else if (obj instanceof Double) {
            comando.setDouble(index, (Double) obj);
        } else if (obj instanceof Long) {
            comando.setLong(index, (Long) obj);
        } else if (obj == null) {
            comando.setNull(index, java.sql.Types.NULL);
        } else {
            throw new SQLException("Tipo de dato no soportado.");
        }
    }

    public int ejecutarAccion() throws SQLException {
        if (comando == null) {
            throw new SQLException("El comando no ha sido inicializado.");
        }
        int filasAfectadas = 0;
        try {
            filasAfectadas = comando.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
        return filasAfectadas;
    }

    private void abrirConexion() {
        try {
            conexion = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void commit() throws SQLException {
        if (conexion != null && !conexion.isClosed()) {
            conexion.commit();
        }
    }
    public void rollback() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.rollback();
                System.out.println("Transacción revertida.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
