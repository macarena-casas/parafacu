package negocioImpl;

import java.util.ArrayList;
import daoImpl.ClienteDaoImpl;
import entidad.Cliente;
import negocio.ClienteNegocio;

public class ClienteNegocioImpl implements ClienteNegocio {
	
	private ClienteDaoImpl clienteDao = new ClienteDaoImpl();

	 @Override
	    public boolean insert(Cliente cliente_a_agregar) {
	        return clienteDao.insert(cliente_a_agregar);
	    }

	    @Override
	    public String delete(Cliente cliente_a_eliminar) {
	        return clienteDao.delete(cliente_a_eliminar);
	    }

	    @Override
	    public boolean update(Cliente cliente_a_modificar) {
	        return clienteDao.update(cliente_a_modificar);
	    }

	    @Override
	    public Cliente get(String dni) {
	        return clienteDao.get(dni);
	    }

	    @Override
	    public ArrayList<Cliente> list() {
	        return clienteDao.list();
	    }

	    @Override
	    public boolean existeCliente(String dni) {
	        return clienteDao.existeCliente(dni);
	    }

		@Override
		public Cliente getPorIdUsuario(int idUsuario) {
			return clienteDao.getPorIdUsuario(idUsuario);
		}
}
