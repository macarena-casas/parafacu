package dao;

import java.util.ArrayList;

import entidad.Cuota;
import entidad.Prestamo;

public interface CuotaDao {

		//public boolean update(Cuota cuota_a_modificar);
			public ArrayList<Cuota> listPorIdPrestamo(Prestamo prestamo);
			
			public int update(int idCuota, String nroCuenta);
		
	}

