package negocioImpl;

import java.math.BigDecimal;

import daoImpl.TipoPrestamoDaoImpl;
import entidad.TipoPrestamo;
import negocio.TipoPrestamoNegocio;

public class TipoPrestamoNegocioImpl implements TipoPrestamoNegocio {

    private TipoPrestamoDaoImpl tipoPrestamoDao = new TipoPrestamoDaoImpl();

    @Override
    public TipoPrestamo get(int cuotas ,BigDecimal Monto) {
        return tipoPrestamoDao.get(cuotas,Monto);
    }

	@Override
	public TipoPrestamo get(int idtipoprestamo) {
		return tipoPrestamoDao.get(idtipoprestamo);
	}
}
