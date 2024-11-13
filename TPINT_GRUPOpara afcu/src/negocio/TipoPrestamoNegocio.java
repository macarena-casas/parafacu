package negocio;

import java.math.BigDecimal;

import entidad.TipoPrestamo;

public interface TipoPrestamoNegocio {
	public TipoPrestamo get(int cuotas, BigDecimal Monto);
	public TipoPrestamo get(int tipoprestamoid);

}
