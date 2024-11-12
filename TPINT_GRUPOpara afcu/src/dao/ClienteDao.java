package dao;

import java.util.ArrayList;

import entidad.Cliente;

public interface ClienteDao {
	
	
	public boolean insert(Cliente cliente_a_agregar);
	public String delete(Cliente cliente_a_eliminar);
	public boolean update(Cliente cliente_a_modificar);
	public boolean prestamosPorCliente(String dni);
	public Cliente getPorIdUsuario(int idUsuario);
	public Cliente get(String dni);
	public ArrayList<Cliente> list();
	boolean existeCliente(String dni);

}