package entidad;

import java.math.BigDecimal;
import java.sql.Date;

public class Cuota {
	private int Idcuota;
	private int Nrocuota;
	private Date Fechavencimiento;
	private BigDecimal Importe;
	private Date Fechapago;

	public Cuota() {
		Idcuota = -1;
		Nrocuota = 0;
		Fechavencimiento = Date.valueOf("1900-01-01");
		Importe = new BigDecimal("0");
		Fechapago = Date.valueOf("1900-01-01");
	}

	public Cuota(int cuotaId, int numeroCuota, Date fechaVencimiento, BigDecimal importe, Date fechaPago) {
		Idcuota = cuotaId;
		Nrocuota = numeroCuota;
		Fechavencimiento = fechaVencimiento;
		Importe = importe;
		Fechapago = fechaPago;
	}

	public int getIdcuota() {
		return Idcuota;
	}

	public void setIdcuota(int idcuota) {
		Idcuota = idcuota;
	}

	public int getNrocuota() {
		return Nrocuota;
	}

	public void setNrocuota(int nrocuota) {
		Nrocuota = nrocuota;
	}

	public Date getFechavencimiento() {
		return Fechavencimiento;
	}

	public void setFechavencimiento(Date fechavencimiento) {
		Fechavencimiento = fechavencimiento;
	}

	public BigDecimal getImporte() {
		return Importe;
	}

	public void setImporte(BigDecimal importe) {
		Importe = importe;
	}

	public Date getFechapago() {
		return Fechapago;
	}

	public void setFechapago(Date fechapago) {
		Fechapago = fechapago;
	}
	

}
