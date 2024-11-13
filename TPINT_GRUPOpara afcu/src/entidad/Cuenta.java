package entidad;

import java.math.BigDecimal;
import java.sql.Date;

public class Cuenta {
	
	private Cliente cliente;
	private Date fechaCreacion;
	private String tipoCuenta;
	private String Cbu;
	private int nroCuenta;
	private BigDecimal Saldo;
	private boolean Estado;
	
	public Cuenta(Cliente cliente, Date fechaCreacion, String tipoCuenta, String cbu, int nroCuenta, BigDecimal saldo,
			boolean estado) {
		this.cliente = cliente;
		this.fechaCreacion = fechaCreacion;
		this.tipoCuenta = tipoCuenta;
		Cbu = cbu;
		this.nroCuenta = nroCuenta;
		Saldo = saldo;
		Estado = true;
	}

	public Cuenta() {
		this.cliente = null;
		this.fechaCreacion = Date.valueOf("1900-01-01");
		this.tipoCuenta = null;
		Cbu = "";
		this.setNroCuenta (-1);
		Saldo = new BigDecimal("0");
		
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public String getCbu() {
		return Cbu;
	}

	public void setCbu(String cbu) {
		Cbu = cbu;
	}

	public int getNroCuenta() {
		return nroCuenta;
	}

	public void setNroCuenta(int nroCuenta) {
		this.nroCuenta = nroCuenta;
	}

	public BigDecimal getSaldo() {
		return Saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		Saldo = saldo;
	}

	public boolean isEstado() {
		return Estado;
	}

	public void setEstado(boolean estado) {
		Estado = estado;
	}
	
	
	
	

}