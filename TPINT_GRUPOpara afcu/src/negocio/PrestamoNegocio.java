package negocio;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;

import entidad.Prestamo;


public interface PrestamoNegocio {

	public ArrayList<Prestamo> obtenerPrestamosSinDni( Date fechaInicio, Date fechaFin,String estadoPrestamo, BigDecimal importeMin, BigDecimal importeMax);
	public ArrayList<Prestamo> obtenerPrestamosPorDni(String dniCliente, Date fechaInicio, Date fechaFin,String estadoPrestamo, BigDecimal importeMin, BigDecimal importeMax);
	public ArrayList<Prestamo> list();
	
	public boolean update(int idPrestamo, String estado);
	public boolean update(int idPrestamo, int cuentaDestino );
	public boolean update(int idPrestamo);
	
	public boolean insert(Prestamo prestamo_agregar);
	public Prestamo get(int idPrestamo);
}
