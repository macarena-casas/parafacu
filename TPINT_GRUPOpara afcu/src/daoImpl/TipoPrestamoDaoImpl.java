package daoImpl;

import java.math.BigDecimal;
import java.sql.ResultSet;
import dao.TipoPrestamoDao;
import entidad.TipoPrestamo;

public class TipoPrestamoDaoImpl implements TipoPrestamoDao {

    private static final String OBTENER_POR_MONTO_Y_CUOTAS = 
        "SELECT * FROM tipos_prestamo WHERE importe_total = ? AND nro_cuotas = ?";
    private static final String OBTENER_POR_ID = 
        "SELECT * FROM tipos_prestamo WHERE tipo_prestamo_id = ?";

    @Override
    public TipoPrestamo get(int cuotas, BigDecimal monto) {
        TipoPrestamo tipoPrestamo = null;
        Conexion conexion = new Conexion();

        try {
            conexion.setearConsulta(OBTENER_POR_MONTO_Y_CUOTAS);
            conexion.setearParametros(1, monto);
            conexion.setearParametros(2, cuotas);

            ResultSet resultSet = conexion.ejecutarLectura();

            if (resultSet.next()) {
                tipoPrestamo = new TipoPrestamo(
                    resultSet.getInt("tipo_prestamo_id"),
                    resultSet.getInt("nro_cuotas"),
                    resultSet.getBigDecimal("importe_total"),
                    resultSet.getBigDecimal("cuota_mensual"),
                    resultSet.getBigDecimal("importe_intereses"),
                    resultSet.getBigDecimal("interes_anual")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexion.cerrarConexion();
        }

        return tipoPrestamo;
    }

    @Override
    public TipoPrestamo get(int idTipoPrestamo) {
        TipoPrestamo tipoPrestamo = null;
        Conexion conexion = new Conexion();

        try {
            conexion.setearConsulta(OBTENER_POR_ID);
            conexion.setearParametros(1, idTipoPrestamo);

            ResultSet resultSet = conexion.ejecutarLectura();

            if (resultSet.next()) {
                tipoPrestamo = new TipoPrestamo(
                    resultSet.getInt("tipo_prestamo_id"),
                    resultSet.getInt("nro_cuotas"),
                    resultSet.getBigDecimal("importe_total"),
                    resultSet.getBigDecimal("cuota_mensual"),
                    resultSet.getBigDecimal("importe_intereses"),
                    resultSet.getBigDecimal("interes_anual")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexion.cerrarConexion();
        }

        return tipoPrestamo;
    }
}
