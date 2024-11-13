package servlets;

import java.io.IOException;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import daoImpl.Conexion;

@WebServlet("/ServletSesion")
public class ServletSesion extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ServletSesion() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String btnCerrarSesion = request.getParameter("btnCerrarSesion");

        if (btnCerrarSesion != null && btnCerrarSesion.equals("true")) {
          
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();  
            }
            response.sendRedirect("Login.jsp"); 
        } else {
            
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
                    
                    HttpSession session = request.getSession();
                    session.setAttribute("userId", userId);
                    session.setAttribute("tipo_usuario_id", tipousuario);

                    response.sendRedirect("Home.jsp"); 
                } else {
                    
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
