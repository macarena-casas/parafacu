package dao;

import java.util.ArrayList;

import entidad.Provincia;

public interface ProvinciaDao {
	
	public Provincia get(int idProvincia);
	public ArrayList<Provincia> list();
	
}
