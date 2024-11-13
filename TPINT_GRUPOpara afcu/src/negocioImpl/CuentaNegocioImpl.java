package negocioImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import daoImpl.ClienteDaoImpl;
import daoImpl.CuentaDaoImpl;
import entidad.Cliente;
import entidad.Cuenta;
import negocio.ClienteNegocio;
import negocio.CuentaNegocio;

public class CuentaNegocioImpl implements CuentaNegocio {

    private CuentaDaoImpl cuentaDao = new CuentaDaoImpl();

    @Override
    public boolean insert(Cuenta cuenta) {
        return cuentaDao.insert(cuenta);
    }

    @Override
    public String delete(int nroCuenta) {
        return cuentaDao.delete(nroCuenta);
    }

    @Override
    public boolean update(int nroCuenta, String tipoCuenta, BigDecimal saldo) {
        return cuentaDao.update(nroCuenta, tipoCuenta,saldo);
    }

    @Override
    public Cuenta get(int nroCuenta) {
        return cuentaDao.get(nroCuenta);
    }

    @Override
    public ArrayList<Cuenta> list() {
        return cuentaDao.list();
    }

	@Override
	public int getLastId() {
		return cuentaDao.getLastId();
	}

	@Override
	public ArrayList<Cuenta> listCuentasPorCliente(String dni) {
		return  cuentaDao.listCuentasPorCliente(dni);
	}
	





}
