package daoImpl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import entidad.Cliente;
import entidad.Cuenta;
import entidad.Localidad;
import entidad.Prestamo;
import entidad.Usuario;
import dao.ClienteDao;
import dao.CuentaDao;
import daoImpl.UsuarioDaoImpl;
import daoImpl.LocalidadDaoImpl;

public class CuentaDaoImpl implements CuentaDao {

	private static final String list = "SELECT c.*, t.tipo_cuenta FROM cuentas c JOIN tiposcuentas t ON c.id_tipoCuenta = t.id_tipoCuenta WHERE c.estado = 1";
	private static final String listPorDni = "SELECT c.*, t.tipo_cuenta FROM cuentas c JOIN tiposcuentas t ON c.id_tipoCuenta = t.id_tipoCuenta WHERE c.estado = 1 and c.dni = ?";
	private static final String insert = "INSERT INTO cuentas(id_tipoCuenta, cbu, dni, fecha_creacion) VALUES((SELECT id_tipoCuenta FROM tiposcuentas WHERE tipo_cuenta = ?), ?, ?, CURDATE())";
	private static final String delete = "UPDATE cuentas SET estado = 0 where numero_cuenta = ?";
	private static final String prestamosPorCuenta = "SELECT COUNT(*) FROM prestamos WHERE numero_cuenta = ? AND estado_prestamo <> 'Rechazado' AND estado = 'Vigente'";
	private static final String update = "UPDATE cuentas SET id_tipoCuenta = (SELECT id_tipoCuenta FROM tiposcuentas WHERE tipo_cuenta = ?), saldo = ? WHERE numero_cuenta = ?";
	private static final String get = "SELECT c.*, t.tipo_cuenta FROM cuentas c JOIN tiposcuentas t ON c.id_tipoCuenta = t.id_tipoCuenta WHERE c.numero_cuenta = ?";
	private static final String getlastId = "SELECT c.numero_cuenta FROM cuentas c ORDER BY c.numero_cuenta DESC LIMIT 1";

	private Conexion conexion;

	public CuentaDaoImpl() {
		this.conexion = new Conexion();
	}

	@Override
	public ArrayList<Cuenta> list() {

		ArrayList<Cuenta> list_cuentas = new ArrayList<Cuenta>();
		try {
			conexion.setearConsulta(list);
			ResultSet resultSet = conexion.ejecutarLectura();

			while (resultSet.next()) {
				ClienteDaoImpl clienteDaoImpl = new ClienteDaoImpl();
				Cliente cliente = clienteDaoImpl.get(resultSet.getString("dni"));

				list_cuentas.add(
						new Cuenta(cliente, resultSet.getDate("fecha_creacion"), resultSet.getString("tipo_cuenta"),
								resultSet.getString("cbu"), resultSet.getInt("numero_cuenta"),
								resultSet.getBigDecimal("saldo"), resultSet.getBoolean("estado")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.cerrarConexion();
		}
		return list_cuentas;
	}

	@Override
	public ArrayList<Cuenta> listCuentasPorCliente(String dni) {

		ArrayList<Cuenta> list_cuentas = new ArrayList<Cuenta>();
		try {
			conexion.setearConsulta(listPorDni);
			conexion.setearParametros(1, dni);

			ResultSet result_set = conexion.ejecutarLectura();

			while (result_set.next()) {
				ClienteDaoImpl clienteDaoImpl = new ClienteDaoImpl();
				Cliente cliente = clienteDaoImpl.get(result_set.getString("dni"));

				list_cuentas.add(
						new Cuenta(cliente, result_set.getDate("fecha_creacion"), result_set.getString("tipo_cuenta"),
								result_set.getString("cbu"), result_set.getInt("numero_cuenta"),
								result_set.getBigDecimal("saldo"), result_set.getBoolean("estado")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.cerrarConexion();
		}
		return list_cuentas;

	}

	@Override
	public boolean insert(Cuenta cuenta_a_agregar) {

		try {

			conexion.setearConsulta(insert);

			conexion.setearParametros(1, String.valueOf(cuenta_a_agregar.getTipoCuenta()));
			conexion.setearParametros(2, cuenta_a_agregar.getCbu());
			conexion.setearParametros(3, cuenta_a_agregar.getCliente().getDni());

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
	public String delete(int nroCuenta) {

		String respuesta = null;

		try {
			if (!prestamosPorCuenta(nroCuenta)) {
				conexion.setearConsulta(delete);
				conexion.setearParametros(1, nroCuenta);

				if (conexion.ejecutarAccion() > 0) {
					conexion.commit();
					respuesta = "La cuenta nro " + nroCuenta + " fue eliminada exitosamente";
				} else {
					respuesta = "La cuenta nro " + nroCuenta + " no se pudo eliminar";
				}
			} else {
				respuesta = "La cuenta tiene préstamos vigentes y no puede darse de baja.";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			conexion.rollback();
		} finally {
			conexion.cerrarConexion();
		}
		return respuesta;

	}

	@Override
	public boolean prestamosPorCuenta(int nroCuenta) {
		boolean tienePrestamos = false;

		try {
			conexion.setearConsulta(prestamosPorCuenta);
			conexion.setearParametros(1, nroCuenta);
			ResultSet resultSet = conexion.ejecutarLectura();

			if (resultSet.next()) {
				int prestamosActivos = resultSet.getInt(1);
				tienePrestamos = prestamosActivos > 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.cerrarConexion();
		}
		return tienePrestamos;
	}

	@Override
	public boolean update(int nroCuenta, String tipoCuenta, BigDecimal saldo) {

		boolean isUpdateExitoso = false;

		try {
			conexion.setearConsulta(update);
			conexion.setearParametros(1, tipoCuenta);
			conexion.setearParametros(2, saldo);
			conexion.setearParametros(3, nroCuenta);

			if (conexion.ejecutarAccion() > 0) {
				conexion.commit();
				isUpdateExitoso = true;
			}
		} catch (

		SQLException e) {
			e.printStackTrace();
			conexion.rollback();
		} finally {
			conexion.cerrarConexion();
		}
		return isUpdateExitoso;
	}

	@Override
	public Cuenta get(int nroCuenta) {
		Cuenta cuenta = null;
		try {
			conexion.setearConsulta(get);
			conexion.setearParametros(1, nroCuenta);
			ResultSet result_set = conexion.ejecutarLectura();
			while (result_set.next()) {
				ClienteDaoImpl clienteDaoImpl = new ClienteDaoImpl();
				Cliente cliente = clienteDaoImpl.get(result_set.getString("dni"));

				cuenta = new Cuenta(cliente, result_set.getDate("fecha_creacion"), result_set.getString("tipo_cuenta"),
						result_set.getString("cbu"), result_set.getInt("numero_cuenta"),
						result_set.getBigDecimal("saldo"), result_set.getBoolean("estado"));
				return cuenta;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.cerrarConexion();
		}
		return cuenta;
	}

	@Override
	public int getLastId() {

		try {
			conexion.setearConsulta(getlastId);
			ResultSet result_set = conexion.ejecutarLectura();
			while (result_set.next()) {
				int lastId = result_set.getInt("numero_cuenta");
				return lastId;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conexion.cerrarConexion();
		}
		return 0;
	}

}
