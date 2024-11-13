package entidad;

public class Localidad {
	private int Idlocalidad;
	private String nombre;
	private Provincia provincia;
    
	public Localidad() {
		this.Idlocalidad = -1;
		this.nombre = "";
		this.provincia = null;
	}
	
	public Localidad(int localidadId, String nombre, Provincia provincia) {
		Idlocalidad = localidadId;
		this.nombre = nombre;
		this.provincia = provincia;
	}

	public int getIdlocalidad() {
		return Idlocalidad;
	}

	public void setIdlocalidad(int idlocalidad) {
		Idlocalidad = idlocalidad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}


	

}
