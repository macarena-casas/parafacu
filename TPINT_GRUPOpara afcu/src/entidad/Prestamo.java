package entidad;

import java.sql.Date;
import java.util.ArrayList;

public class Prestamo {
	private int IdPrestamo;
	private TipoPrestamo Tipoprestamo;
	private Date Fecha;
	private ArrayList<Cuota> Cuotas = new ArrayList<Cuota>();
	private Cuenta cuenta;
	private Cliente cliente;
    private int Plazopago;
    private String Estado;

    
    
	public Prestamo() {
		IdPrestamo = -1;
		Tipoprestamo = null;
		Fecha = Date.valueOf("01-01-1900");
	
		this.cuenta = null;
		this.cliente = null;
		Plazopago = -1;
		Estado = "En proceso";
	}



	public Prestamo(int idPrestamo, TipoPrestamo tipoprestamo, Date fecha, Cuenta cuenta,
			Cliente cliente, int plazopago, String estado) {
	
		IdPrestamo = idPrestamo;
		Tipoprestamo = tipoprestamo;
		Fecha = fecha;
		this.cuenta = cuenta;
		this.cliente = cliente;
		Plazopago = plazopago;
		Estado = estado;
	}
	public Prestamo( Cuenta cuenta, TipoPrestamo tipoPrestamo, Date fecha, String estado,int plazopago) {
		
		Tipoprestamo = tipoPrestamo;
		Fecha = fecha;
		this.cuenta = cuenta;
		Plazopago = plazopago;
		Estado = estado;
	}

    // gets y sets

	public int getIdPrestamo() {
		return IdPrestamo;
	}



	public void setIdPrestamo(int idPrestamo) {
		IdPrestamo = idPrestamo;
	}



	public TipoPrestamo getTipoprestamo() {
		return Tipoprestamo;
	}



	public void setTipoprestamo(TipoPrestamo tipoprestamo) {
		Tipoprestamo = tipoprestamo;
	}



	public Date getFecha() {
		return Fecha;
	}



	public void setFecha(Date fecha) {
		Fecha = fecha;
	}



	public ArrayList<Cuota> getCuotas() {
		return Cuotas;
	}



	public void setCuotas(ArrayList<Cuota> cuotas) {
		Cuotas = cuotas;
	}



	public Cuenta getCuenta() {
		return cuenta;
	}



	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}



	public Cliente getCliente() {
		return cliente;
	}



	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}



	public int getPlazopago() {
		return Plazopago;
	}



	public void setPlazopago(int plazopago) {
		Plazopago = plazopago;
	}



	public String getEstado() {
		return Estado;
	}



	public void setEstado(String estado) {
		Estado = estado;
	}
    

	
 
	


    
}
