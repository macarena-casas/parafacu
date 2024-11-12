package entidad;

import java.sql.Date;

public class Cliente {
    private String dni;
    private String cuil;
    private String nombre;
    private String apellido;
    private char sexo;
    private String nacionalidad;
    private Date fechaNacimiento;
    private String direccion;
    private Localidad localidad;
    private String correo;
    private String telefono;
    private Usuario usuario;
    
    public Cliente() {
        this.dni = "5";
        this.cuil = "5";
        this.nombre = "5";
        this.apellido = "5";
        this.sexo = 'O';
        this.nacionalidad = "5";
        this.fechaNacimiento = Date.valueOf("1900-01-01");
        this.direccion = "5";
        this.localidad = null;
        this.correo = "5";
        this.telefono = "5";
        this.usuario = null;
	}
    
    public Cliente(String dni, String cuil, String nombre, String apellido, char sexo,
			String nacionalidad, Date fechaNacimiento, String direccion, Localidad localidad, String correo,
			String telefono, Usuario usuario) {
		this.dni = dni;
		this.cuil = cuil;
		this.nombre = nombre;
		this.apellido = apellido;
		this.sexo = sexo;
		this.nacionalidad = nacionalidad;
		this.fechaNacimiento = fechaNacimiento;
		this.direccion = direccion;
		this.localidad = localidad;
		this.correo = correo;
		this.telefono = telefono;
		this.usuario = usuario;
	}
    
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getCuil() {
		return cuil;
	}
	public void setCuil(String cuil) {
		this.cuil = cuil;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public char getSexo() {
		return sexo;
	}
	public void setSexo(char sexo) {
		this.sexo = sexo;
	}
	public String getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public Localidad getLocalidad() {
		return localidad;
	}
	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}