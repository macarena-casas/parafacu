package entidad;

public class Pais {
	private int Idpais;
	private String Nombre;

	public Pais() {
		this.Idpais = -1;
		this.Nombre = "";
	}
	
	public Pais(int paisId, String nombre) {
		this.Idpais = paisId;
		this.Nombre = nombre;

	}

	public int getIdpais() {
		return Idpais;
	}

	public void setIdpais(int idpais) {
		Idpais = idpais;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	
}
