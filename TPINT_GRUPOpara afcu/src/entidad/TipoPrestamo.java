package entidad;

import java.math.BigDecimal;

public class TipoPrestamo {
	private int IdtipoPrestamo;
	private int NroCuotas;
	private BigDecimal ImporteTotal;
	private BigDecimal CuotaMensual;
	private BigDecimal ImporteIntereses;
	private BigDecimal InteresAnual;

	public TipoPrestamo() {

		IdtipoPrestamo = 0;
		NroCuotas = 0;
		ImporteTotal = new BigDecimal(0);
		CuotaMensual = new BigDecimal(0);
		ImporteIntereses = new BigDecimal(0);
		InteresAnual = new BigDecimal(0);
	}

	public TipoPrestamo(int idtipoPrestamo, int nroCuotas, BigDecimal importeTotal, BigDecimal cuotaMensual,
			BigDecimal importeIntereses, BigDecimal interesAnual) {

		IdtipoPrestamo = idtipoPrestamo;
		NroCuotas = nroCuotas;
		ImporteTotal = importeTotal;
		CuotaMensual = cuotaMensual;
		ImporteIntereses = importeIntereses;
		InteresAnual = interesAnual;
	}
    public TipoPrestamo( BigDecimal importeTotal, BigDecimal importeIntereses, int nroCuotas, BigDecimal cuotaMensual) {
    	this.IdtipoPrestamo = 0;
    	this.NroCuotas = nroCuotas;
        this.ImporteTotal = importeTotal;
        this.CuotaMensual = cuotaMensual;
        this.ImporteIntereses = importeIntereses;
        this.InteresAnual =  new BigDecimal(0);
    }
	
	// sets y gets
	public int getIdtipoPrestamo() {
		return IdtipoPrestamo;
	}

	public void setIdtipoPrestamo(int idtipoPrestamo) {
		IdtipoPrestamo = idtipoPrestamo;
	}

	public int getNroCuotas() {
		return NroCuotas;
	}

	public void setNroCuotas(int nroCuotas) {
		NroCuotas = nroCuotas;
	}

	public BigDecimal getImporteTotal() {
		return ImporteTotal;
	}

	public void setImporteTotal(BigDecimal importeTotal) {
		ImporteTotal = importeTotal;
	}

	public BigDecimal getCuotaMensual() {
		return CuotaMensual;
	}

	public void setCuotaMensual(BigDecimal cuotaMensual) {
		CuotaMensual = cuotaMensual;
	}

	public BigDecimal getImporteIntereses() {
		return ImporteIntereses;
	}

	public void setImporteIntereses(BigDecimal importeIntereses) {
		ImporteIntereses = importeIntereses;
	}

	public BigDecimal getInteresAnual() {
		return InteresAnual;
	}

	public void setInteresAnual(BigDecimal interesAnual) {
		InteresAnual = interesAnual;
	}
	@Override
	public boolean equals(Object obj) {
		
		TipoPrestamo other = (TipoPrestamo) obj;
		
		if (
				CuotaMensual.equals(other.CuotaMensual)&&
				ImporteIntereses.equals(other.ImporteIntereses)&&
				ImporteTotal.equals(other.ImporteTotal)&&
				NroCuotas == other.NroCuotas
				
				)
		{
			return true;
		}
		return true;
	}


}
