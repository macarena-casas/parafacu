package negocioImpl;

import entidad.Usuario;
import java.util.ArrayList;
import daoImpl.UsuarioDaoImpl;
import negocio.UsuarioNegocio;

public class UsuarioNegocioImpl implements UsuarioNegocio {

    private UsuarioDaoImpl usuarioDao = new UsuarioDaoImpl(); 

    @Override
    public boolean insert(Usuario usuario_a_agregar) {
        return usuarioDao.insert(usuario_a_agregar);
    }

    @Override
    public boolean update(Usuario usuario_a_modificar) {
        return usuarioDao.update(usuario_a_modificar);
    }

    @Override
    public Usuario get(int idUsuario) {
        return usuarioDao.get(idUsuario);
    }

    @Override
    public ArrayList<Usuario> list() {
        return usuarioDao.list();
    }

    @Override
    public Usuario buscarUsuario(String nombreUsuario) {
        return usuarioDao.buscarUsuario(nombreUsuario);
    }

    @Override
    public boolean existeNombreUsuario(String nombre) {
        return usuarioDao.existeNombreUsuario(nombre);
    }
}
