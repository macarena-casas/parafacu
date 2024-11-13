package daoImpl;

import java.sql.ResultSet;
import java.util.ArrayList;

import dao.ProvinciaDao;
import entidad.Pais;
import entidad.Provincia;

public class ProvinciaDaoImpl implements ProvinciaDao {

    private static final String LISTAR_PROVINCIAS = "SELECT * FROM provincias";
    private static final String OBTENER_PROVINCIA = "SELECT * FROM provincias WHERE provincia_id = ?";

    @Override
    public Provincia get(int prov_id) {
        Provincia provincia = null;
        Conexion conexion = new Conexion();
        try {
            conexion.setearConsulta(OBTENER_PROVINCIA);
            conexion.setearParametros(1, prov_id);
            ResultSet resultSet = conexion.ejecutarLectura();

            if (resultSet.next()) {
                String nombreProvincia = resultSet.getString("nombre");
                int paisId = resultSet.getInt("pais_id");
                
                // Obtener el país asociado utilizando el DAO
                PaisDaoImpl paisDao = new PaisDaoImpl();
                Pais pais = paisDao.get(paisId);

                provincia = new Provincia(prov_id, nombreProvincia, pais);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexion.cerrarConexion();
        }
        return provincia;
    }

    @Override
    public ArrayList<Provincia> list() {
        ArrayList<Provincia> listaProvincias = new ArrayList<>();
        Conexion conexion = new Conexion();
        try {
            conexion.setearConsulta(LISTAR_PROVINCIAS);
            ResultSet resultSet = conexion.ejecutarLectura();

            // Instanciar el PaisDao una sola vez
            PaisDaoImpl paisDao = new PaisDaoImpl();

            while (resultSet.next()) {
                int provinciaId = resultSet.getInt("provincia_id");
                String nombreProvincia = resultSet.getString("nombre");
                int paisId = resultSet.getInt("pais_id");

                // Obtener el país asociado
                Pais pais = paisDao.get(paisId);

                Provincia provincia = new Provincia(provinciaId, nombreProvincia, pais);
                listaProvincias.add(provincia);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexion.cerrarConexion();
        }
        return listaProvincias;
    }
}
