package daoImpl;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.PrestamoDao;
import entidad.Prestamo;
import entidad.Cliente;
import entidad.Cuenta;
import entidad.TipoPrestamo;

public class PrestamoDaoImpl implements PrestamoDao {
    private static final String updateEstado = "UPDATE prestamos SET estado_prestamo = ? WHERE prestamo_id = ?";
    private static final String updateEstadoCancelado = "UPDATE prestamos SET estado = ? WHERE prestamo_id = ?";
    private static final String insert = "INSERT INTO prestamos(numero_cuenta, fecha, plazo_pago, tipo_prestamo_id, estado_prestamo) VALUES(?, ?, ?, ?, ?)";
    private static final String list = "SELECT p.*, cli.nombre, cli.apellido FROM prestamos p JOIN cuentas c ON p.numero_cuenta = c.numero_cuenta JOIN clientes cli ON c.dni = cli.dni WHERE p.estado_prestamo = 'En proceso'";
    private static final String get = "SELECT * FROM prestamos WHERE prestamo_id = ?";
    private static final String call = "CALL SP_AUTORIZAR_PRESTAMO(?, ?)";

    private Conexion conexion;

    public PrestamoDaoImpl() {
        this.conexion = new Conexion();
    }

    @Override
    public boolean insert(Prestamo prestamo) {
        try {
            conexion.setearConsulta(insert);
            conexion.setearParametros(1, prestamo.getCuenta().getNroCuenta());
            conexion.setearParametros(2, prestamo.getFecha());
            conexion.setearParametros(3, prestamo.getPlazopago());
            conexion.setearParametros(4, prestamo.getTipoprestamo().getIdtipoPrestamo());
            conexion.setearParametros(5, "En proceso");

            if (conexion.ejecutarAccion() > 0) {
                conexion.commit();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            conexion.rollback();
        } finally {
            conexion.cerrarConexion();
        }
        return false;
    }

    @Override
    public boolean update(int idPrestamo, String estado) {
        try {
            conexion.setearConsulta(updateEstado);
            conexion.setearParametros(1, estado);
            conexion.setearParametros(2, idPrestamo);

            if (conexion.ejecutarAccion() > 0) {
                conexion.commit();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            conexion.rollback();
        } finally {
            conexion.cerrarConexion();
        }
        return false;
    }

    @Override
    public boolean update(int idPrestamo, int cuentaDestino) {
        try {
            conexion.setearConsulta(call);
            conexion.setearParametros(1, idPrestamo);
            conexion.setearParametros(2, cuentaDestino);

            if (conexion.ejecutarAccion() > 0) {
                conexion.commit();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            conexion.rollback();
        } finally {
            conexion.cerrarConexion();
        }
        return false;
    }

    @Override
    public Prestamo get(int idPrestamo) {
        Prestamo prestamo = null;
        try {
            conexion.setearConsulta(get);
            conexion.setearParametros(1, idPrestamo);
            ResultSet resultSet = conexion.ejecutarLectura();

            if (resultSet.next()) {
                int prestamoId = resultSet.getInt("prestamo_id");
                Date fecha = resultSet.getDate("fecha");
                int plazoPago = resultSet.getInt("plazo_pago");
                int tipoPrestamoId = resultSet.getInt("tipo_prestamo_id");
                String estadoPrestamo = resultSet.getString("estado_prestamo");

                TipoPrestamo tipoPrestamo = new TipoPrestamoDaoImpl().get(tipoPrestamoId);
                Cuenta cuenta = new Cuenta(); // Puedes obtener más detalles de la cuenta si necesitas
                Cliente cliente = new Cliente(); // Igual con el cliente

                prestamo = new Prestamo(prestamoId, tipoPrestamo, fecha, cuenta, cliente, plazoPago, estadoPrestamo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexion.cerrarConexion();
        }
        return prestamo;
    }

    @Override
    public ArrayList<Prestamo> list() {
        ArrayList<Prestamo> listPrestamos = new ArrayList<>();
        try {
            conexion.setearConsulta(list);
            ResultSet resultSet = conexion.ejecutarLectura();

            while (resultSet.next()) {
                int prestamoId = resultSet.getInt("prestamo_id");
                Date fecha = resultSet.getDate("fecha");
                int plazoPago = resultSet.getInt("plazo_pago");
                int tipoPrestamoId = resultSet.getInt("tipo_prestamo_id");
                String estadoPrestamo = resultSet.getString("estado_prestamo");
                String nombre = resultSet.getString("nombre");
                String apellido = resultSet.getString("apellido");

                Cliente cliente = new Cliente();
                cliente.setApellido(apellido);
                cliente.setNombre(nombre);

                TipoPrestamo tipoPrestamo = new TipoPrestamoDaoImpl().get(tipoPrestamoId);
                Cuenta cuenta = new Cuenta(); // Puedes agregar más detalles si necesitas

                Prestamo prestamo = new Prestamo(prestamoId, tipoPrestamo, fecha, cuenta, cliente, plazoPago, estadoPrestamo);
                listPrestamos.add(prestamo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexion.cerrarConexion();
        }
        return listPrestamos;
    }

	@Override
	public boolean update(int idPrestamo) {
	    boolean updateExitoso = false;
	    Conexion conexion = new Conexion();
	    try {
	        conexion.setearConsulta("UPDATE prestamos SET estado_prestamo = ? WHERE prestamo_id = ?");
	        conexion.setearParametros(1, "Cancelado");
	        conexion.setearParametros(2, idPrestamo);

	        if (conexion.ejecutarAccion() > 0) {
	            conexion.commit();
	            updateExitoso = true;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        conexion.rollback();
	    } finally {
	        conexion.cerrarConexion();
	    }
	    return updateExitoso;
	}


	@Override
	public ArrayList<Prestamo> listIdPrestamosPorCliente(String dni) {
	    ArrayList<Prestamo> listPrestamos = new ArrayList<>();
	    Conexion conexion = new Conexion();
	    try {
	        conexion.setearConsulta("SELECT prestamo_id, plazo_pago FROM prestamos WHERE cliente_dni = ?");
	        conexion.setearParametros(1, dni);
	        ResultSet resultSet = conexion.ejecutarLectura();

	        while (resultSet.next()) {
	            int prestamoId = resultSet.getInt("prestamo_id");
	            int plazoPago = resultSet.getInt("plazo_pago");

	            Prestamo prestamo = new Prestamo();
	            prestamo.setIdPrestamo(prestamoId);
	            prestamo.setPlazopago(plazoPago);

	            listPrestamos.add(prestamo);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        conexion.cerrarConexion();
	    }
	    return listPrestamos;
	}


	@Override
	public ArrayList<Prestamo> obtenerPrestamosSinDni(Date fechaInicio, Date fechaFin, String estadoPrestamo, BigDecimal importeMin, BigDecimal importeMax) {
	    ArrayList<Prestamo> listaPrestamos = new ArrayList<>();
	    Conexion conexion = new Conexion();
	    try {
	        conexion.setearConsulta("CALL obtenerPrestamosSinDni(?, ?, ?, ?, ?)");
	        conexion.setearParametros(1, fechaInicio);
	        conexion.setearParametros(2, fechaFin);
	        conexion.setearParametros(3, estadoPrestamo);
	        conexion.setearParametros(4, importeMin);
	        conexion.setearParametros(5, importeMax);
	        ResultSet resultSet = conexion.ejecutarLectura();

	        while (resultSet.next()) {
	        	 int idprestamo = resultSet.getInt("prestamo_id");
	                int nroCuenta = resultSet.getInt("numero_cuenta");
	                Date fecha = resultSet.getDate("fecha");
	                int plazoPago = resultSet.getInt("plazo_pago");
	                int idtipoPrestamo = resultSet.getInt("tipo_prestamo_id");
	                Cuenta cuenta = new Cuenta();
	                Cliente cliente = new Cliente();
	                TipoPrestamo tipoPrestamo  = new TipoPrestamo();
	                
	                TipoPrestamoDaoImpl tipoPrestamoDaoImpl = new TipoPrestamoDaoImpl();
	                tipoPrestamo = tipoPrestamoDaoImpl.get(idtipoPrestamo);
	                
	               // CuentaDaoImpl cuentaDaoImpl = new CuentaDaoImpl();
	               // cuenta =  cuentaDaoImpl.get(nroCuenta);
	                
	                ClienteDaoImpl clienteDaoImpl = new ClienteDaoImpl();
	                //cliente clienteDaoImpl.get(cuenta.getCliente().getDni());

	                Prestamo prestamo = new Prestamo(idprestamo, tipoPrestamo,fecha, cuenta,cliente, plazoPago, estadoPrestamo);
	                listaPrestamos.add(prestamo);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        conexion.cerrarConexion();
	    }
	    return listaPrestamos;
	}


	@Override
	public ArrayList<Prestamo> obtenerPrestamosPorDni(String dniCliente, Date fechaInicio, Date fechaFin,
	                                                  String estadoPrestamo, BigDecimal importeMin, BigDecimal importeMax) {
	    ArrayList<Prestamo> listaPrestamos = new ArrayList<>();
	    Conexion conexion = new Conexion();
	    
	    try {
	        conexion.setearConsulta("CALL obtenerPrestamosPorDni(?, ?, ?, ?, ?, ?)");
	        conexion.setearParametros(1, dniCliente);
	        conexion.setearParametros(2, fechaInicio);
	        conexion.setearParametros(3, fechaFin);
	        conexion.setearParametros(4, estadoPrestamo);
	        conexion.setearParametros(5, importeMin);
	        conexion.setearParametros(6, importeMax);
	        
	        ResultSet resultSet = conexion.ejecutarLectura();

	        // Instanciar los DAOs una sola vez fuera del bucle para optimizar el rendimiento
	        TipoPrestamoDaoImpl tipoPrestamoDao = new TipoPrestamoDaoImpl();
	        

	        while (resultSet.next()) {
	            int prestamoId = resultSet.getInt("prestamo_id");
	            int numeroCuenta = resultSet.getInt("numero_cuenta");
	            Date fecha = resultSet.getDate("fecha");
	            int plazoPago = resultSet.getInt("plazo_pago");
	            int tipoPrestamoId = resultSet.getInt("tipo_prestamo_id");

	            Cuenta cuenta = new Cuenta();
                Cliente cliente = new Cliente();
                TipoPrestamo tipoPrestamo  = new TipoPrestamo();
                
                TipoPrestamoDaoImpl tipoPrestamoDaoImpl = new TipoPrestamoDaoImpl();
                tipoPrestamo = tipoPrestamoDaoImpl.get(tipoPrestamoId);
                
               // CuentaDaoImpl cuentaDaoImpl = new CuentaDaoImpl();
              //  cuenta =  cuentaDaoImpl.get(numeroCuenta);
                
                ClienteDaoImpl clienteDaoImpl = new ClienteDaoImpl();
               // cliente = clienteDaoImpl.get(cuenta.getCliente().getDni());

                Prestamo prestamo = new Prestamo(prestamoId, tipoPrestamo,fecha, cuenta,cliente, plazoPago, estadoPrestamo);
                listaPrestamos.add(prestamo);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        conexion.cerrarConexion();
	    }
	    return listaPrestamos;
	}

}
