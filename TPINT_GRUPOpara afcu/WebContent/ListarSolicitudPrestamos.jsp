<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entidad.Prestamo" %>
<%@ page import="entidad.TipoPrestamo" %>
<%@ page import="entidad.Cliente" %>

 <%
       String respuesta = null;
	   if(session != null && session.getAttribute("respuesta") != null){
	   respuesta = (String)session.getAttribute("respuesta");
	   session.removeAttribute("respuesta");
	   %>
	   <script> 
	   	 alert('<%= respuesta%>');
	   </script>   
	   <%
	   respuesta = null;}
%>


<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Autorización de Préstamos</title>
<link
	href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.11.4/css/jquery.dataTables.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" charset="utf8"
	src="https://cdn.datatables.net/1.11.4/js/jquery.dataTables.js"></script>
<link rel="icon" type="image/png" href="Images/logo.png">
</head>
<body>

	<jsp:include page="NavBar.jsp" />
	<div class="bg-white pt-24">

		<div class="container mx-auto px-4 py-8"
			style="width: 80%; overflow-y: auto; min-height: 600px;">

			<h2
				class="sm:text-3xl text-2xl font-medium title-font mb-2 text-gray-900"
				style="margin-top: 3%;">Autorización de Préstamos</h2>
			<br> <br>
			<table id="tablaClientes" class="table table-striped table-bordered"
				style="border: 2px; color: solid green;">

				<thead>
					<tr>
						<th>N° Préstamo</th>
						<th>Cliente</th>
						<th>Importe Solicitado</th>
						<th>Cant. Cuotas</th>
						<th>Cuotas</th>
						<th>Fecha</th>
						<th>Autorizar</th>
						<th>Rechazar</th>
					</tr>
				</thead>
				<tbody>
					<% if (request.getAttribute("Lista_Prestamos") != null) {
               ArrayList<Prestamo> listaPrestamos = (ArrayList<Prestamo>) request.getAttribute("Lista_Prestamos");
               for (Prestamo prestamo : listaPrestamos) { %>
					<tr>
						<td style="text-align: center"><%= prestamo.getIdPrestamo() %></td>
						<td style="text-align: center"><%= prestamo.getCliente().getNombre() %>
							<%= prestamo.getCliente().getApellido() %></td>
						<td style="text-align: center">$<%= prestamo.getTipoprestamo().getImporteTotal().toString() %></td>
						<td style="text-align: center"><%= prestamo.getTipoprestamo().getNroCuotas() %></td>
						<td style="text-align: center">$<%= prestamo.getTipoprestamo().getCuotaMensual().toString() %></td>
						<td style="text-align: center"><%= prestamo.getFecha().toString() %></td>
						<td style="text-align: center">
							<form action="ServletPrestamosAdmin" method="post"
								onsubmit="return confirmacion('autorizar');">
								<input type="hidden" name="numeroPrestamo"
									value="<%= prestamo.getIdPrestamo() %>"> <input
									type="hidden" name="cuentaDestino"
									value="<%= prestamo.getCuenta().getNroCuenta() %>">
								<button type="submit" name="btnAutorizar"
									class="flex mx-auto mt-6 text-black bg-purple-500 border-0 py-2 px-5 focus:outline-none hover:bg-purple-600 rounded btn btn-success">Autorizar</button>
							</form>
						</td>
						<td>
							<form action="ServletPrestamosAdmin" method="post"
								onsubmit="return confirmacion('rechazar');">
								<input type="hidden" name="numeroPrestamo"
									value="<%= prestamo.getIdPrestamo() %>">
								<button type="submit" name="btnRechazar"
									class="flex mx-auto mt-6 text-white bg-red-500 border-0 py-2 px-5 focus:outline-none hover:bg-red-600 rounded">Rechazar</button>
							</form>
						</td>
					</tr>

					<% 
					}  
             } else {
            	 %>
            
					<tr>
						<td colspan="9" style="text-align: center">No hay solicitudes
							de préstamos disponibles</td>
					</tr>
 <%   } %>
				</tbody>
			</table>
			<br>
			<div class="flex justify-end w-full mt-4">
				<a href="MenuAdmin.jsp"
					class="text-white bg-purple-500 border-0 py-2 px-8 focus:outline-none hover:bg-purple-600 rounded text-lg btn btn-success">Volver
					al menú</a>
			</div>
		</div>


	</div>


	<jsp:include page="Footer.jsp" />

</body>
</html>

