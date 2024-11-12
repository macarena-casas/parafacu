package negocioImpl;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;

import daoImpl.PrestamoDaoImpl;
import entidad.Prestamo;
import negocio.PrestamoNegocio;

public class PrestamoNegocioImpl implements PrestamoNegocio {
    private PrestamoDaoImpl prestamoDao = new PrestamoDaoImpl();

    @Override
    public ArrayList<Prestamo> list() {
    	return prestamoDao.list();
    }
    @Override
    public ArrayList<Prestamo> obtenerPrestamosSinDni(Date fechaInicio, Date fechaFin, String estadoPrestamo,
    		BigDecimal importeMin, BigDecimal importeMax) {
    	return prestamoDao.obtenerPrestamosSinDni(fechaInicio,fechaFin, estadoPrestamo,importeMin,importeMax);
    	
    }
    
    @Override
    public ArrayList<Prestamo> obtenerPrestamosPorDni(String dniCliente, Date fechaInicio, Date fechaFin,
    		String estadoPrestamo, BigDecimal importeMin, BigDecimal importeMax) {
    	return prestamoDao.obtenerPrestamosPorDni(dniCliente, fechaInicio,fechaFin,estadoPrestamo,importeMin,importeMax);
    }
    
    public ArrayList<Prestamo> listIdPrestamosPorCliente(String dni){
    	return prestamoDao.listIdPrestamosPorCliente(dni);
    }


	@Override
	public boolean update(int idPrestamo, String estado) {
		return prestamoDao.update(idPrestamo, estado);
	}

	@Override
	public boolean update(int idPrestamo, int cuentaDestino) {
		return prestamoDao.update(idPrestamo, cuentaDestino);
	}

	@Override
	public boolean update(int idPrestamo) {
		return prestamoDao.update(idPrestamo);
	}

	
	@Override
	public boolean insert(Prestamo prestamo_a_agregar) {
		return prestamoDao.insert(prestamo_a_agregar);
	}
	
	@Override
	public Prestamo get(int idPrestamo) {
		return prestamoDao.get(idPrestamo);
	}
}
