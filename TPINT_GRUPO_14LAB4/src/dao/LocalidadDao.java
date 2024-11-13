package dao;

import java.util.ArrayList;

import entidad.Localidad;

public interface LocalidadDao {

	
	public Localidad get(int idLocalidad);
	public ArrayList<Localidad> list();

}
