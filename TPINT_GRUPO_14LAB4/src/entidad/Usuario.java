package entidad;

public class Usuario {
	
		private int Idusuario;
		private String Nombreusuario;
		private String Contraseña;
		private TipoUsuario tipoUsuario;

		public Usuario() {
			Idusuario = -1;
			Nombreusuario = "";
			Contraseña= "";
			tipoUsuario = new TipoUsuario();
		}
		
		public Usuario(int usuarioId, String nombreUsuario, String contraseña, TipoUsuario tipoUsuario) {
			Idusuario = usuarioId;
			Nombreusuario = nombreUsuario;
			Contraseña = contraseña;
			tipoUsuario = tipoUsuario;
		}

		public int getIdusuario() {
			return Idusuario;
		}

		public void setIdusuario(int idusuario) {
			Idusuario = idusuario;
		}

		public String getNombreusuario() {
			return Nombreusuario;
		}

		public void setNombreusuario(String nombreusuario) {
			Nombreusuario = nombreusuario;
		}

		public String getContraseña() {
			return Contraseña;
		}

		public void setContraseña(String contraseña) {
			Contraseña = contraseña;
		}

		public TipoUsuario getTipoUsuario() {
			return tipoUsuario;
		}

		public void setTipoUsuario(TipoUsuario tipoUsuario) {
			this.tipoUsuario = tipoUsuario;
		}
		
		
}

		


