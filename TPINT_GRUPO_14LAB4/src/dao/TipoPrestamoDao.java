package dao;

import java.math.BigDecimal;
import java.util.ArrayList;

import entidad.TipoPrestamo;

public interface TipoPrestamoDao {
	
	public TipoPrestamo get(int cuotas ,BigDecimal Monto);
	public TipoPrestamo get(int tipoprestamoid);
	
}
