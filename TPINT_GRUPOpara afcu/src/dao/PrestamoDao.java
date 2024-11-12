package dao;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;

import entidad.Prestamo;

public interface PrestamoDao {
	
	public boolean insert(Prestamo prestamo_a_agregar);
	
	public boolean update(int idPrestamo, String estado);
	public boolean update(int idPrestamo, int cuentaDestino );
	public boolean update(int idPrestamo);

	public ArrayList<Prestamo> list();
	public ArrayList<Prestamo> listIdPrestamosPorCliente(String dni);
	
	public ArrayList<Prestamo> obtenerPrestamosSinDni( Date fechaInicio, Date fechaFin,String estadoPrestamo, BigDecimal importeMin, BigDecimal importeMax);
	public ArrayList<Prestamo> obtenerPrestamosPorDni(String dniCliente, Date fechaInicio, Date fechaFin,String estadoPrestamo, BigDecimal importeMin, BigDecimal importeMax);

	public Prestamo get(int idPrestamo);
	
}
