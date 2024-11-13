package negocio;

import java.math.BigDecimal;
import java.util.ArrayList;

import entidad.Cuenta;

public interface CuentaNegocio {
	public boolean insert(Cuenta cuenta);
	String delete(int nroCuenta);
	public boolean update(int nroCuenta, String tipoCuenta, BigDecimal saldo);
	public ArrayList<Cuenta> listCuentasPorCliente(String dni);

	public Cuenta get(int nroCuenta);
	public ArrayList<Cuenta> list();	
	public int getLastId();
}
