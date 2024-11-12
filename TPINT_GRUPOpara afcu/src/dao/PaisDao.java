package dao;

import java.util.ArrayList;

import entidad.Pais;

public interface PaisDao {
	
	public Pais get(int idPais);
	public ArrayList<Pais> list();
	
}
