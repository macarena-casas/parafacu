package entidad;

public class Provincia {
	private int Idprovincia;
	private String nombre;
	private Pais pais;

	public Provincia() {
		this.Idprovincia = -1;
		this.nombre = "";
		this.pais = null;
	}
	
	public Provincia(int provinciaId, String nombre, Pais pais) {
		this.Idprovincia = provinciaId;
		this.nombre = nombre;
		this.pais = pais;
	}

	public int getIdprovincia() {
		return Idprovincia;
	}

	public void setIdprovincia(int idprovincia) {
		Idprovincia = idprovincia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}
	

}
