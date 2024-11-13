package negocio;

import java.util.ArrayList;

import entidad.Cuota;
import entidad.Prestamo;

public interface CuotaNegocio {

	int update(int idCuota, String nroCuenta);

	ArrayList<Cuota> listPorIdPrestamo(Prestamo prestamo);

}
