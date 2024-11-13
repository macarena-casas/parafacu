package servlets;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import daoImpl.Conexion;
import entidad.Cliente;
import entidad.Usuario;
import negocioImpl.ClienteNegocioImpl;
import negocioImpl.CuentaNegocioImpl;
import negocioImpl.UsuarioNegocioImpl;

@WebServlet("/ServletSesion")
public class ServletSesion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ClienteNegocioImpl clienteNegocioImpl = new ClienteNegocioImpl();

	public ServletSesion() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String btnCerrarSesion = request.getParameter("btnCerrarSesion");

		if (btnCerrarSesion != null && btnCerrarSesion.equals("true")) {

			session = request.getSession(false);
			if (session != null) {
				session.invalidate();
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Login.jsp");
			dispatcher.forward(request, response);

		} else {
			UsuarioNegocioImpl usuarioNegocioImpl = new UsuarioNegocioImpl();

			String username = request.getParameter("username");
			String password = request.getParameter("password");

			try {
			
				Conexion cone = new Conexion();
				cone.setearConsulta("SELECT * FROM usuarios WHERE nombre_usuario = ? AND password = ?");
				cone.setearParametros(1, username);
				cone.setearParametros(2, password);
				ResultSet rs = cone.ejecutarLectura();

				if (rs.next()) {
					int userId = rs.getInt("usuario_id");
					int tipousuario = rs.getInt("tipo_usuario_id");

					session.setAttribute("userId", userId);
					session.setAttribute("tipo_usuario_id", tipousuario);
					session.setAttribute("usuario", username);

					if (tipousuario == 1) {

						session.setAttribute("tipoUsuario", "admin");

						RequestDispatcher dispatcher = request.getRequestDispatcher("/MenuAdmin.jsp");
						dispatcher.forward(request, response);
					} else {
						Cliente auxCliente = clienteNegocioImpl.getPorIdUsuario(userId);
						session.setAttribute("cliente", auxCliente);
						session.setAttribute("tipoUsuario", "cliente");

						CuentaNegocioImpl cuentaNegocio = new CuentaNegocioImpl();
						int cantCuentas = cuentaNegocio.listCuentasPorCliente(auxCliente.getDni()).size();
						session.setAttribute("cantCuentas", cantCuentas);

						RequestDispatcher dispatcher = request.getRequestDispatcher("/MenuCliente.jsp");
						dispatcher.forward(request, response);
					}
					// response.sendRedirect("Home.jsp");

				}

				else {

					response.sendRedirect("Login.jsp?error=1");
				}
				rs.close();
				cone.cerrarConexion();

			} catch (Exception e) {
				e.printStackTrace();
				response.sendRedirect("Login.jsp?error=1");
			}
		}
	}

}
