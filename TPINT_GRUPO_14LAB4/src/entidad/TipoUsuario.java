package entidad;

public class TipoUsuario {
    private int IdTipousuario;
    private String Tipousuario;

    public TipoUsuario() {
        IdTipousuario = 0;
        Tipousuario = "";
    }

    public TipoUsuario(int tipo_usuario_id, String tipo_usuario){
    	IdTipousuario = tipo_usuario_id;
        Tipousuario = tipo_usuario;
    }

	public int getIdTipousuario() {
		return IdTipousuario;
	}

	public void setIdTipousuario(int idTipousuario) {
		IdTipousuario = idTipousuario;
	}

	public String getTipousuario() {
		return Tipousuario;
	}

	public void setTipousuario(String tipousuario) {
		Tipousuario = tipousuario;
	}

	
}
