package servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidad.Cliente;
import entidad.Cuenta;
import entidad.Cuota;
import entidad.Prestamo;
import entidad.TipoPrestamo;
import negocioImpl.CuentaNegocioImpl;
import negocioImpl.PrestamoNegocioImpl;
import negocioImpl.TipoPrestamoNegocioImpl;



@WebServlet("/ServletPrestamosAdmin")
public class ServletPrestamosAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TipoPrestamoNegocioImpl tipoPrestamoImpl = new TipoPrestamoNegocioImpl();
	private PrestamoNegocioImpl prestamoImpl = new PrestamoNegocioImpl();
	private ArrayList<TipoPrestamo> listaTipoPrestamos = new ArrayList<TipoPrestamo>();
	private ArrayList<Prestamo> listaPrestamos = new ArrayList<Prestamo>();
	TipoPrestamo tipoPrestamoS = new TipoPrestamo();
	
	private CuentaNegocioImpl cuentaNegocioImpl = new CuentaNegocioImpl();
	private ArrayList<Cuenta> cuentasPorCliente = new ArrayList<Cuenta>();

    public ServletPrestamosAdmin() {
    	super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
  

		int cuotasSeleccionadas =12;
        BigDecimal montoSeleccionado = new BigDecimal("500000.00");
        TipoPrestamo auxTipoPrestamo = tipoPrestamoImpl.get(cuotasSeleccionadas, montoSeleccionado);

      /*request.setAttribute("Monto", auxTipoPrestamo.getImporteTotal());
        request.setAttribute("Cuotas", auxTipoPrestamo.getNroCuotas());
        request.setAttribute("interes", auxTipoPrestamo.getInteresAnual());
        request.setAttribute("valorCuota", auxTipoPrestamo.getCuotaMensual());
        request.setAttribute("montoFinal", auxTipoPrestamo.getImporteIntereses());
        request.setAttribute("Lista_Cuentas_cliente", cuentasPorCliente);
     */
		if (request.getParameter("btnPrestamos") != null)
	    {
			
			listaPrestamos = prestamoImpl.list();
	        request.setAttribute("Lista_Prestamos", listaPrestamos);
	        
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("/ListarSolicitudPrestamos.jsp");
			dispatcher.forward(request, response);
	    }
		
		if(request.getParameter("btnSolicitarPrestamos") != null)
	    {
			int cantCuentas = (int) session.getAttribute("cantCuentas");
		
			
			if(cantCuentas !=0) {
				Cliente cliente =(Cliente)session.getAttribute("cliente");
				cuentasPorCliente = cuentaNegocioImpl.listCuentasPorCliente(cliente.getDni());
			    request.setAttribute("Lista_Cuentas_cliente", cuentasPorCliente);
		    	RequestDispatcher dispatcher = request.getRequestDispatcher("/SolicitarPrestamo.jsp");
				dispatcher.forward(request, response);
			}
			else {
				session.setAttribute("respuesta", "No tiene cuentas disponibles para acceder a esta opcion.");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/MenuCliente.jsp");
				dispatcher.forward(request, response);		
			}
	    }
		
     }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String monto = request.getParameter("Monto");
	    String cuotas = request.getParameter("Cuotas");
	    String interes = request.getParameter("interes");
	    String valorCuota = request.getParameter("valorCuota");
	    String montoFinal = request.getParameter("montoFinal");
	    String nroCuenta = request.getParameter("CuentaDestino");
	    



	     if (request.getParameter("btnSolicitar") != null  ) {
	        
	    	monto = request.getParameter("Monto");
		    cuotas = request.getParameter("Cuotas");
		    interes = request.getParameter("interes");
		    valorCuota = request.getParameter("valorCuota");
		    montoFinal = request.getParameter("montoFinal");
	    	int cuotasSeleccionadas = Integer.parseInt(cuotas);
		    BigDecimal montoSeleccionado = new BigDecimal(monto);
		    BigDecimal interesSeleccionado = new BigDecimal(interes);
		    BigDecimal valorCuotaSeleccionado = new BigDecimal(valorCuota);
	        Cuenta auxCuenta = new Cuenta();
	       auxCuenta.setNroCuenta(Integer.parseInt(request.getParameter("CuentaDestino")));
		    TipoPrestamo auxTipoPrestamo = tipoPrestamoImpl.get(cuotasSeleccionadas, montoSeleccionado);

	     
	         
	        long millis = System.currentTimeMillis();
            Date fecha =  new Date(millis);
           int plazoPago = auxTipoPrestamo.getNroCuotas();
             
            Prestamo prestamo = new Prestamo(
          		  auxCuenta,
          		  auxTipoPrestamo,
          		  fecha,
          		  "En proceso",
          		 plazoPago)
            		;
             
            boolean inserto = prestamoImpl.insert(prestamo);

            if(inserto) {
            	session.setAttribute("respuesta", "Se solicito el prestamo");
            }
            else {
            	session.setAttribute("respuesta", "No se pudo solicitar el prestamo");
            }
            
            
           
            

    		int cuotasSeleccionadas1 =12;
            BigDecimal montoSeleccionado1 = new BigDecimal("500000.00");
           TipoPrestamo auxTipoPrestamo1 = tipoPrestamoImpl.get(cuotasSeleccionadas1, montoSeleccionado1);

          
           

            request.setAttribute("Monto", auxTipoPrestamo1.getImporteTotal());
            request.setAttribute("Cuotas", auxTipoPrestamo1.getNroCuotas());
            request.setAttribute("interes", auxTipoPrestamo1.getInteresAnual());
            request.setAttribute("valorCuota", auxTipoPrestamo1.getCuotaMensual());
            request.setAttribute("montoFinal", auxTipoPrestamo1.getImporteIntereses());
            request.setAttribute("Lista_Cuentas_cliente", cuentasPorCliente);
            
            
            Cliente cliente = (Cliente)session.getAttribute("cliente");
			cuentasPorCliente = cuentaNegocioImpl.listCuentasPorCliente(cliente.getDni());
		    request.setAttribute("Lista_Cuentas_cliente", cuentasPorCliente);
         	listaPrestamos = prestamoImpl.list();
         	
         	RequestDispatcher dispatcher = request.getRequestDispatcher("/SolicitarPrestamo.jsp");
 	        dispatcher.forward(request, response);
	        
	        
	    }
	     else if (request.getParameter("Cuotas") == null && request.getParameter("btnSolicitar")!= null  ) {
	    	    session.setAttribute("respuesta", "Elegir monto y y cuotas");
	    	    Cliente cliente = (Cliente)session.getAttribute("cliente");
				cuentasPorCliente = cuentaNegocioImpl.listCuentasPorCliente(cliente.getDni());
			    request.setAttribute("Lista_Cuentas_cliente", cuentasPorCliente);
	         	listaPrestamos = prestamoImpl.list();
		        RequestDispatcher dispatcher = request.getRequestDispatcher("/SolicitarPrestamo.jsp");
		        dispatcher.forward(request, response);
		        
		    } 
	     else if (request.getParameter("Cuotas") != null && request.getParameter("btnSolicitar")== null  ) {
		    	
		        int cuotasSeleccionadas = Integer.parseInt(cuotas);
		        BigDecimal montoSeleccionado = new BigDecimal(monto);
	            TipoPrestamo auxTipoPrestamo = tipoPrestamoImpl.get(cuotasSeleccionadas, montoSeleccionado);

		        BigDecimal montoInteres = auxTipoPrestamo.getImporteIntereses();
		        montoFinal = montoSeleccionado.add(montoInteres).toString();
		        interes = montoInteres.toString();
		        valorCuota = auxTipoPrestamo.getCuotaMensual().toString();

		        request.setAttribute("Monto", monto);
		        request.setAttribute("Cuotas", cuotas);
		        request.setAttribute("interes", auxTipoPrestamo.getInteresAnual());
		        request.setAttribute("valorCuota", auxTipoPrestamo.getCuotaMensual());
		        request.setAttribute("montoFinal", auxTipoPrestamo.getImporteIntereses());
		        request.setAttribute("Lista_Cuentas_cliente", cuentasPorCliente);

		        RequestDispatcher dispatcher = request.getRequestDispatcher("/SolicitarPrestamo.jsp");
		        dispatcher.forward(request, response);
		        
		    } 
		    
	    
	    else if(request.getParameter("btnAutorizar") != null) {
	    	
	    	boolean autorizo = prestamoImpl.update(Integer.parseInt((request.getParameter("numeroPrestamo"))),Integer.parseInt(request.getParameter("cuentaDestino")));

            if(autorizo) {
            	session.setAttribute("respuesta", "Prestamo aprobado con exito");
            }
            else {
            	session.setAttribute("respuesta", "Error. No fue posible aprobar el prestamo");
            }
            
            listaPrestamos = prestamoImpl.list();
 	        
            request.setAttribute("Lista_Prestamos", listaPrestamos);
 	        
 	    	RequestDispatcher dispatcher = request.getRequestDispatcher("/ListarSolicitudPrestamos.jsp");
 			dispatcher.forward(request, response);
	    	
	    } else if (request.getParameter("btnRechazar") != null) {
	    	
	    	boolean rechazado = prestamoImpl.update(Integer.parseInt((request.getParameter("numeroPrestamo"))),"Rechazado");
	    	
	        if(rechazado) {
	        	session.setAttribute("respuesta", "Prestamo rechazado con exito");
            }
            else {
            	session.setAttribute("respuesta", "Error. No fue posible aprobar el prestamo");
            }
	    	listaPrestamos = prestamoImpl.list();
	        request.setAttribute("Lista_Prestamos", listaPrestamos);
	        
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("/ListarSolicitudPrestamos.jsp");
			dispatcher.forward(request, response);
	    }
	}

}
