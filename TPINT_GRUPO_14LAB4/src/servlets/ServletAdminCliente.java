package servlets;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import negocioImpl.ClienteNegocioImpl;
import negocioImpl.UsuarioNegocioImpl;
import negocioImpl.LocalidadNegocioImpl;
import negocioImpl.ProvinciaNegocioImpl;
import negocioImpl.TipoUsuarioNegocioImpl;
import negocioImpl.PaisNegocioImpl;

import entidad.Cliente;
import entidad.Localidad;
import entidad.Pais;
import entidad.Provincia;
import entidad.TipoUsuario;
import entidad.Usuario;



@WebServlet("/ServletAdminCliente")
public class ServletAdminCliente extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ArrayList<Cliente> listaClientes1 = new ArrayList<Cliente>();
    private ArrayList<Localidad> listaLocalidad = new ArrayList<Localidad>();
    private ArrayList<Provincia> listaProvincia = new ArrayList<Provincia>();    
    private ArrayList<Pais> listaPais = new ArrayList<Pais>();

	private ClienteNegocioImpl clienteNegocioImpl = new ClienteNegocioImpl();
	private UsuarioNegocioImpl usuarioNegocioImpl = new UsuarioNegocioImpl();
	private LocalidadNegocioImpl localidadNegocioImpl = new LocalidadNegocioImpl();
	private ProvinciaNegocioImpl provinciaNegocioImpl = new ProvinciaNegocioImpl();
	private PaisNegocioImpl paisNegocioImpl = new PaisNegocioImpl();
	
    public ServletAdminCliente() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	HttpSession session = request.getSession();  
    	
    	if(session == null || session.getAttribute("tipoUsuario") != "admin") {
    		RequestDispatcher dispatcher = request.getRequestDispatcher("/Login.jsp");
    		dispatcher.forward(request, response);
    	}
    	
    	
    	// condicion cuando este logeado
    	if (request.getParameter("btnAgregarCliente") != null) {
    		cargarDesplegables(request);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/AgregarCliente.jsp");
            dispatcher.forward(request, response);
            
        }else if (request.getParameter("btnAdminClientes") != null) {
			listaClientes1 = clienteNegocioImpl.list();
            request.setAttribute("Lista_Clientes", listaClientes1);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/ListarClientes.jsp");
            dispatcher.forward(request, response);
            
        }else if (request.getParameter("btnDetalle") != null) {
            String dni = request.getParameter("dni");
            Cliente auxCliente = (Cliente)listaClientes1.stream().filter(x -> x.getDni().equals(dni)).findFirst().orElse(null);
            request.setAttribute("ClienteDetalle", auxCliente);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/DetalleCliente.jsp");
            dispatcher.forward(request, response);
            
        }else if (request.getParameter("btnModificar") != null) {
            String dni = request.getParameter("dni");
            Cliente auxCliente = clienteNegocioImpl.get(dni);
            request.setAttribute("ClienteDetalle", auxCliente);
            cargarDesplegables(request);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/ModificarCliente.jsp");
            dispatcher.forward(request, response);
            
        }else if(request.getParameter("btnEliminar") != null) {
        	String dni = request.getParameter("dni");
			String respuesta = clienteNegocioImpl.delete(clienteNegocioImpl.get(dni));
            session.setAttribute("respuesta", respuesta);
        	listaClientes1 = clienteNegocioImpl.list();
            request.setAttribute("Lista_Clientes", listaClientes1);   
        	RequestDispatcher dispatcher = request.getRequestDispatcher("/ListarClientes.jsp");
        	dispatcher.forward(request, response);
        }    
    }
             
            

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();  
    	if(session == null || session.getAttribute("tipoUsuario") != "admin") {
    		RequestDispatcher dispatcher = request.getRequestDispatcher("/Login.jsp");
    		dispatcher.forward(request, response);
    	}
    	
    	if(request.getParameter("btnGuardarCambios") != null) {
			try{
				String dni = request.getParameter("dni");
				Cliente auxCliente = (Cliente)listaClientes1.stream().filter(x -> x.getDni().equals(dni)).findFirst().orElse(null);
				
			/*	Provincia provincia = provinciaNegocioImpl.get(Integer.parseInt(request.getParameter("provincia")));
				Localidad localidad = localidadNegocioImpl.get(Integer.parseInt(request.getParameter("localidad")));

				if(localidad.getProvincia().getProvinciaId() != provincia.getProvinciaId()){
					throw new Exception("La localidad no pertenece a la provincia seleccionada");
				}*/
				String regex = "[a-zA-ZñÑáéíóúÁÉÍÓÚ\\s]+"; //Solo admite caracteres alfabeticos
				Pattern pattern = Pattern.compile(regex);
				Matcher matcher = pattern.matcher(request.getParameter("nombre"));
				if(!matcher.matches()) {
					throw new Exception("El nombre debe contener exclusivamente caracteres alfabeticos");
				}

				Cliente modCliente = new Cliente(
            			/*request.getParameter("dni"),
            			request.getParameter("cuil"),
            			request.getParameter("nombre"),
            			request.getParameter("apellido"),
            			request.getParameter("sexo").charAt(0),
            			paisNegocioImpl.get(Integer.parseInt(request.getParameter("nacionalidad"))).getNombre(),
            			Date.valueOf(request.getParameter("fecha-nacimiento")),
            			request.getParameter("direccion"),
            			localidadNegocioImpl.get(Integer.parseInt(request.getParameter("localidad"))),
            			request.getParameter("Email"),
            			request.getParameter("telefonos"),
            			new Usuario()*/ //Falta desarrolar Localidad y provincia
            			);
            	
        		Boolean cli_modificado = clienteNegocioImpl.update(modCliente);       		
        		Cliente cliente = clienteNegocioImpl.get(request.getParameter("dni"));
        		/*Usuario modUser = usuarioNegocioImpl.get(cliente.getUsuario().getIdusuario());
        		modUser.setContraseña((String) request.getParameter("contrasena"));
        		Boolean user_modificado = usuarioNegocioImpl.update(modUser);        		
        		cliente.setUsuario(modUser);
        		
        		if(cli_modificado && user_modificado) {
        		session.setAttribute("respuesta", "Los cambios se guardaron exitosamente");
        		listaClientes1 = clienteNegocioImpl.list();
	            request.setAttribute("Lista_Clientes", listaClientes1);   
        		 RequestDispatcher dispatcher = request.getRequestDispatcher("/ClientesListar.jsp");
                dispatcher.forward(request, response);
        		}*/ // falta desarrollar Usuario negocio
			}catch(Exception e){
				session.setAttribute("respuesta", "Error al guardar los cambios: " + e.getMessage());
				listaClientes1 = clienteNegocioImpl.list();
	            request.setAttribute("Lista_Clientes", listaClientes1);   
        		RequestDispatcher dispatcher = request.getRequestDispatcher("/ListarClientes.jsp");
        		dispatcher.forward(request, response);
			}        	
         	
         }
    	 else if(request.getParameter("btnAgregarClienteNuevo") != null) { 
    		       		  
    		   /* Provincia auxProv = (Provincia)listaProvincia.stream().filter(x -> x.getProvinciaId() ==  Integer.parseInt(request.getParameter("provincia"))).findFirst().orElse(null);
    		  //  Localidad auxLoc = (Localidad)listaLocalidad.stream().filter(x -> x.getLocalidadId() ==  Integer.parseInt(request.getParameter("localidad"))).findFirst().orElse(null);
   		    
    		    if(auxProv.getProvinciaId() != auxLoc.getProvincia().getProvinciaId()) {
	  	        	session.setAttribute("respuesta", "La localidad no pertenece a la provincia seleccionada");
	  	        	cargarDesplegables(request);
	  	            RequestDispatcher dispatcher = request.getRequestDispatcher("/AgregarCliente.jsp");
	  	            dispatcher.forward(request, response);
    		    }
    		    else {
	                String auxDni = request.getParameter("dni");       			
	                String  auxCuil = request.getParameter("cuil");
	                String auxEmail = request.getParameter("email"); 
	                String auxUsuario = request.getParameter("usuario");
	                
	                List<Cliente> auxLista = null;
	                listaClientes1 = clienteNegocioImpl.list();
	                auxLista = listaClientes1.stream().filter(x -> x.getDni().equals(auxDni) || x.getCuil().equals(auxCuil) || x.getUsuario().getNombreUsuario().equals(auxUsuario) || x.getCorreo().equals(auxEmail)).collect(Collectors.toList());
	      	
	               if(auxLista==null || auxLista.isEmpty()) {
	      		    
	      		   int idpais =Integer.parseInt( request.getParameter("nacionalidad1")); 
	             //  Pais cpais = paisNegocioImpl.get(idpais); //falta desarrollar paisnegocio
	               int idprovincia = Integer.parseInt(request.getParameter("provincia"));
	   		       Provincia cProvincia = provinciaNegocioImpl.get(idprovincia);
	               int idlocalidad = Integer.parseInt(request.getParameter("localidad"));
	               Localidad cLocalidad = localidadNegocioImpl.get(idlocalidad);
	             //  cProvincia.setPais(cpais); //falta desarrollar provincia negico
	               cLocalidad.setProvincia(cProvincia);        
	               String contraseña = request.getParameter("contrasena");
	               TipoUsuarioNegocioImpl tipoUsuarioNegocioImpl = new TipoUsuarioNegocioImpl();
	               TipoUsuario tipoUsuario = tipoUsuarioNegocioImpl.get(2); //Valor por cliente       
	               Usuario newUsuario = new Usuario();
	               newUsuario.setNombreusuario(auxUsuario);
	               newUsuario.setContraseña(contraseña);
	               newUsuario.setTipoUsuario(tipoUsuario);
	               usuarioNegocioImpl.insert(newUsuario);
	               int idusuario =usuarioNegocioImpl.list().size();
	               newUsuario = usuarioNegocioImpl.get(idusuario); 
	      	       Cliente Clientenuevo = new Cliente(
	            			auxDni,         			
	            			auxCuil,
	            			request.getParameter("nombre"),
	            			request.getParameter("apellido"),
	            			request.getParameter("sexo").charAt(0),
	            			paisNegocioImpl.get(Integer.parseInt(request.getParameter("nacionalidad1"))).getNombre(),
	            			Date.valueOf(request.getParameter("fechanacimiento")),
	            			request.getParameter("direccion"),
	            			cLocalidad,
	            	     	auxEmail,
	            			request.getParameter("telefono"),
	            			newUsuario
	            	);
	                 clienteNegocioImpl.insert(Clientenuevo);*/
    		 // desarrollar loc y prov
	                 session.setAttribute("respuesta", "El cliente fue agregado exitosamente");
	                 cargarDesplegables(request);
	                RequestDispatcher dispatcher = request.getRequestDispatcher("/AgregarCliente.jsp");
	                dispatcher.forward(request, response);
	         
             
             }else {
            	session.setAttribute("respuesta", "Error. El cliente ya se encuentra ingresado.");
            	cargarDesplegables(request);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/AgregarCliente.jsp");
                dispatcher.forward(request, response);

           }
    		    
    	 } 
        
      // }
    //}
    
    
    
    private void cargarDesplegables(HttpServletRequest request) {
    	//listaPais = paisNegocioImpl.list();    	
      	request.setAttribute("Lista_Paises", listaPais);
      	//listaLocalidad = localidadNegocioImpl.list();
      	request.setAttribute("Lista_Localidades", listaLocalidad);
      	//listaProvincia = provinciaNegocioImpl.list();
      	request.setAttribute("Lista_Provincias", listaProvincia);
	}
}

