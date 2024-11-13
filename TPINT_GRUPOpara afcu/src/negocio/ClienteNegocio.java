package negocio;

import java.util.ArrayList;

import entidad.Cliente;

public interface ClienteNegocio {
	public boolean insert(Cliente cliente_a_agregar);
	public String delete(Cliente cliente_a_eliminar);
	public boolean update(Cliente cliente_a_modificar);
	
	
	public Cliente getPorIdUsuario(int idUsuario);
	public Cliente get(String dni);
	public ArrayList<Cliente> list();
	public boolean existeCliente(String dni);
	

}
