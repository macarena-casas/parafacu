package daoImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.mysql.jdbc.CallableStatement;

import entidad.Cliente;
import entidad.Localidad;
import entidad.Usuario;
import dao.ClienteDao;

public class ClienteDaoImpl implements ClienteDao {
    
	private static final String update = "UPDATE clientes SET cuil = ?, nombre = ?, apellido = ?, sexo = ?, nacionalidad = ?, fecha_nacimiento = ?, direccion = ?, localidad_id = ?, provincia_id = ?, correo_electronico = ?, telefono = ? WHERE dni = ?";
	private static final String insert = "INSERT INTO clientes(dni, cuil, nombre, apellido, sexo, nacionalidad, fecha_nacimiento, direccion, localidad_id, correo_electronico, telefono, usuario_id, provincia_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String delete = "CALL SP_BAJA_CLIENTE(?,?)";
	private static final String list = "SELECT * FROM clientes WHERE estado = 1";
	private static final String get = "SELECT * FROM clientes WHERE dni = ?";
	private static final String getId = "SELECT * FROM clientes WHERE usuario_id = ?";
	private static final String queryExiste = "SELECT COUNT(*) FROM clientes WHERE dni = ?";
	private static final String prestamosPorCliente = "SELECT COUNT(*) FROM prestamos as p INNER JOIN cuentas c ON p.numero_cuenta = c.numero_cuenta INNER JOIN clientes cl ON c.dni = cl.dni WHERE cl.dni = ? AND p.estado_prestamo <> 'Rechazado' AND p.estado = 'Vigente'";

	@Override
	public boolean insert(Cliente cliente) {
	    Conexion cone = new Conexion();
	    boolean isInsertExitoso = false;
	    
	    try {
	    	cone.setearConsulta(insert);
	    	cone.setearParametros(1, cliente.getDni());
	    	cone.setearParametros(2, cliente.getCuil());
	    	cone.setearParametros(3, cliente.getNombre());
	    	cone.setearParametros(4, cliente.getApellido());
	    	cone.setearParametros(5, String.valueOf(cliente.getSexo()));
	    	cone.setearParametros(6, cliente.getNacionalidad());
	    	cone.setearParametros(7, cliente.getFechaNacimiento());
	    	cone.setearParametros(8, cliente.getDireccion());
	    	cone.setearParametros(9, cliente.getLocalidad().getIdlocalidad());
	    	cone.setearParametros(10, cliente.getCorreo());
	    	cone.setearParametros(11, cliente.getTelefono());
	    	cone.setearParametros(12, cliente.getUsuario().getIdusuario());
	    	cone.setearParametros(13, cliente.getLocalidad().getProvincia().getIdprovincia());
	    	
	    	int filasafectadas = cone.ejecutarAccion();
	    	if (filasafectadas > 0) {
	    		
	    		isInsertExitoso = true;
	    	}
	    } catch (SQLException e) {
	    	e.printStackTrace();
	    	cone.rollback();
	    }
	    return isInsertExitoso;
	}

	@Override
	public String delete(Cliente cliente) {
	    Conexion cone = new Conexion();
	    String respuesta = null;

	    try {
	    	if (!prestamosPorCliente(cliente.getDni())) {
	    		cone.setearConsulta(delete);
	    		cone.setearParametros(1, cliente.getDni());
	    		cone.setearParametros(2, cliente.getUsuario().getIdusuario());
	    		cone.ejecutarAccion();

	    		respuesta = "El cliente con DNI: " + cliente.getDni() + " fue eliminado exitosamente";
	    	} else {
	    		respuesta = "El cliente tiene prestamos vigentes y no puede darse de baja.";
	    	}
	    } catch (SQLException e) {
	    	e.printStackTrace();
	    	cone.rollback();
			respuesta = "El cliente con DNI: " + cliente.getDni() + " no se pudo eliminar";
	    }
	    return respuesta;
	}

	public boolean prestamosPorCliente(String dni) {
	    Conexion cone = new Conexion();
	    boolean tienePrestamos = false;

	    try {
	    	cone.setearConsulta(prestamosPorCliente);
	    	cone.setearParametros(1, dni);
	    	ResultSet resultSet = cone.ejecutarLectura();
	    	if (resultSet.next()) {
	    		int prestamosActivos = resultSet.getInt(1);
	    		tienePrestamos = prestamosActivos > 0;
	    	}
	    } catch (SQLException e) {
	    	e.printStackTrace();
	    }
	    return tienePrestamos;
	}

	@Override
	public boolean update(Cliente cliente) {
	    Conexion cone = new Conexion();
	    boolean isUpdateExitoso = false;

	    try {
	    	cone.setearConsulta(update);
	    	cone.setearParametros(1, cliente.getCuil());
	    	cone.setearParametros(2, cliente.getNombre());
	    	cone.setearParametros(3, cliente.getApellido());
	    	cone.setearParametros(4, String.valueOf(cliente.getSexo()));
	    	cone.setearParametros(5, cliente.getNacionalidad());
	    	cone.setearParametros(6, cliente.getFechaNacimiento());
	    	cone.setearParametros(7, cliente.getDireccion());
	    	cone.setearParametros(8, cliente.getLocalidad().getIdlocalidad());
	    	cone.setearParametros(9, cliente.getLocalidad().getProvincia().getIdprovincia());
	    	cone.setearParametros(10, cliente.getCorreo());
	    	cone.setearParametros(11, cliente.getTelefono());
	    	cone.setearParametros(12, cliente.getDni());

	    	if (cone.ejecutarAccion() > 0) {
	    		
	    		isUpdateExitoso = true;
	    	}
	    } catch (SQLException e) {
	    	e.printStackTrace();
	    	cone.rollback();
	    }
	    return isUpdateExitoso;
	}

	@Override
	public Cliente get(String dni) {
	    Conexion cone = new Conexion();
	    Cliente cliente = null;

	    try {
	    	cone.setearConsulta(get);
	    	cone.setearParametros(1, dni);
	    	ResultSet resultSet = cone.ejecutarLectura();

	    	if (resultSet.next()) {
	    		UsuarioDaoImpl usuarioDaoImpl = new UsuarioDaoImpl();
	    		LocalidadDaoImpl localidadDaoImpl = new LocalidadDaoImpl();

	    		cliente = new Cliente(
	    			dni,
	    			resultSet.getString("cuil"),
	    			resultSet.getString("nombre"),
	    			resultSet.getString("apellido"),
	    			resultSet.getString("sexo").charAt(0),
	    			resultSet.getString("nacionalidad"),
	    			resultSet.getDate("fecha_nacimiento"),
	    			resultSet.getString("direccion"),
	    			localidadDaoImpl.get(resultSet.getInt("localidad_id")),
	    			resultSet.getString("correo_electronico"),
	    			resultSet.getString("telefono"),
	    			usuarioDaoImpl.get(resultSet.getInt("usuario_id"))
	    		);
	    	}
	    } catch (SQLException e) {
	    	e.printStackTrace();
	    }
	    return cliente;
	}

	@Override
	public Cliente getPorIdUsuario(int idUsuario) {
	    Conexion cone = new Conexion();
	    Cliente cliente = null;

	    try {
	    	cone.setearConsulta(getId);
	    	cone.setearParametros(1, idUsuario);
	    	ResultSet resultSet = cone.ejecutarLectura();

	    	if (resultSet.next()) {
	    		UsuarioDaoImpl usuarioDaoImpl = new UsuarioDaoImpl();
	    		LocalidadDaoImpl localidadDaoImpl = new LocalidadDaoImpl();

	    		cliente = new Cliente(
	    			resultSet.getString("dni"),
	    			resultSet.getString("cuil"),
	    			resultSet.getString("nombre"),
	    			resultSet.getString("apellido"),
	    			resultSet.getString("sexo").charAt(0),
	    			resultSet.getString("nacionalidad"),
	    			resultSet.getDate("fecha_nacimiento"),
	    			resultSet.getString("direccion"),
	    			localidadDaoImpl.get(resultSet.getInt("localidad_id")),
	    			resultSet.getString("correo_electronico"),
	    			resultSet.getString("telefono"),
	    			usuarioDaoImpl.get(resultSet.getInt("usuario_id"))
	    		);
	    	}
	    } catch (SQLException e) {
	    	e.printStackTrace();
	    }
	    return cliente;
	}

	@Override
	public ArrayList<Cliente> list() {
	    Conexion cone = new Conexion();
	    ArrayList<Cliente> listClientes = new ArrayList<>();

	    try {
	    	cone.setearConsulta(list);
	    	ResultSet resultSet = cone.ejecutarLectura();

	    	while (resultSet.next()) {
	    		UsuarioDaoImpl usuarioDaoImpl = new UsuarioDaoImpl();
	    		LocalidadDaoImpl localidadDaoImpl = new LocalidadDaoImpl();

	    		listClientes.add(new Cliente(
	    			resultSet.getString("dni"),
	    			resultSet.getString("cuil"),
	    			resultSet.getString("nombre"),
	    			resultSet.getString("apellido"),
	    			resultSet.getString("sexo").charAt(0),
	    			resultSet.getString("nacionalidad"),
	    			resultSet.getDate("fecha_nacimiento"),
	    			resultSet.getString("direccion"),
	    			localidadDaoImpl.get(resultSet.getInt("localidad_id")),
	    			resultSet.getString("correo_electronico"),
	    			resultSet.getString("telefono"),
	    			usuarioDaoImpl.get(resultSet.getInt("usuario_id"))
	    		));
	    	}
	    } catch (SQLException e) {
	    	e.printStackTrace();
	    }
	    return listClientes;
	}

	@Override
	public boolean existeCliente(String dni) {
	    Conexion cone = new Conexion();
	    boolean existe = false;

	    try {
	    	cone.setearConsulta(queryExiste);
	    	cone.setearParametros(1, dni);
	    	ResultSet resultSet = cone.ejecutarLectura();

	    	if (resultSet.next()) {
	    		existe = resultSet.getInt(1) > 0;
	    	}
	    } catch (SQLException e) {
	    	e.printStackTrace();
	    }
	    return existe;
	}
}
