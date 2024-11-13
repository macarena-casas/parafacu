<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="entidad.*"%>


<%
ArrayList<Cuenta> cuentasPorCliente = (ArrayList<Cuenta>) request.getAttribute("Lista_Cuentas_cliente");
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
<title>Solicitud Prestamo</title>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
<style>
.bgLeft {
	background: linear-gradient(45deg, lightgreen, limegreen, darkgreen, limegreen,
		lightgreen);
	background-size: cover;
}

.bgRight {
	background: linear-gradient(45deg, lightgreen, limegreen, darkgreen, limegreen,
		lightgreen);
	background-size: cover;
}

.ancho {
	width: 100%;
	max-width: 400px;
}
</style>
<script type="text/javascript">
	function confirmarEnvio() {
		return confirm("¿Está seguro de que desea solicitar este prestamo?");
	}
</script>
<%
	String montoSeleccionado = request.getParameter("Monto");
	String cuotasSeleccionadas = request.getParameter("Cuotas");
	String interes = request.getParameter("interes");
	String valorCuota = request.getParameter("valorCuota");
	String montoFinal = request.getParameter("montoFinal");
%>
</head>
<body>
	<jsp:include page="NavBar.jsp" />
	<div class="d-flex">
		<div class="col-1 bgLeft pt-5"></div>
		<div class="col-10 p-4 bg-white bg-opacity-80 pt-5">
			<div class="container py-4">
				<div class="w-75 mx-auto">
					<div class="d-flex flex-column align-items-center w-100">
						<h2 class="text-center font-weight-medium mb-2 text-dark">Solicitud
							de Préstamo</h2>
						<br>
						<form id="prestamoForm"
							class="d-flex flex-column align-items-center w-100"
							action="ServletPrestamosAdmin" method="post">
							<div class="mb-4 ancho">
								<label class="form-label" for="Cuenta de Destino">Cuenta
									de Destino</label>
								<div class="position-relative">
								
								
								
									<select id="CuentaDestino" name="CuentaDestino"
										class="form-select">
						                <% if (cuentasPorCliente != null && !cuentasPorCliente.isEmpty()) { %>
						                    <% for (Cuenta cuenta : cuentasPorCliente) { %>
						                        <option value="<%= cuenta.getNroCuenta() %>">N° <%= cuenta.getNroCuenta() %></option>
						                    <% } %>
						                <% } else { %>

										<option value="">No tiene cuentas disponibles</option>
 <% } %>
									</select>
									<div
										class="position-absolute top-50 end-0 translate-middle-y pe-2 text-gray-700">

									</div>
								</div>
							</div>
							<div class="mb-4 ancho">
								<label class="form-label" for="Monto">Monto a solicitar</label>
								<div class="position-relative">
									<select id="Monto" name="Monto" onchange="this.form.submit()"
										class="form-select">
										<option value="500000"
											<%="500000".equals(montoSeleccionado) ? "selected" : ""%>>500000.00</option>
										<option value="1000000"
											<%="1000000".equals(montoSeleccionado) ? "selected" : ""%>>1000000.00</option>
										<option value="1500000"
											<%="1500000".equals(montoSeleccionado) ? "selected" : ""%>>1500000.00</option>
										<option value="2000000"
											<%="2000000".equals(montoSeleccionado) ? "selected" : ""%>>2000000.00</option>
										<option value="2500000"
											<%="2500000".equals(montoSeleccionado) ? "selected" : ""%>>2500000.00</option>
									</select>
									<div
										class="position-absolute top-50 end-0 translate-middle-y pe-2 text-gray-700">

									</div>
								</div>
							</div>
							<div class="mb-4 ancho">
								<label class="form-label" for="Cuotas">Cantidad de
									cuotas</label>
								<div class="position-relative">
									<select id="Cuotas" name="Cuotas" onchange="this.form.submit()"
										class="form-select">
										<option value="12"
											<%="12".equals(cuotasSeleccionadas) ? "selected" : ""%>>12</option>
										<option value="24"
											<%="24".equals(cuotasSeleccionadas) ? "selected" : ""%>>24</option>
										<option value="36"
											<%="36".equals(cuotasSeleccionadas) ? "selected" : ""%>>36</option>
									</select>
									<div
										class="position-absolute top-50 end-0 translate-middle-y pe-2 text-gray-700">

									</div>
								</div>
							</div>
							<div class="mb-4 ancho">
								<label class="form-label" for="interes">Interés Anual:</label> <input
									required type="text" readonly id="interes" name="interes"
									class="form-control"
									value="<%=request.getAttribute("interes") != null ? request.getAttribute("interes") : ""%>">
							</div>
							<div class="mb-4 ancho">
								<label class="form-label" for="valorCuota">Valor de
									cuota mensual:</label> <input required type="text" readonly
									id="valorCuota" name="valorCuota" class="form-control"
									value="<%=request.getAttribute("valorCuota") != null ? request.getAttribute("valorCuota") : ""%>">
							</div>
							<div class="mb-4 ancho">
								<label class="form-label" for="montoFinal">Monto Final :</label>
								<input required readonly id="montoFinal" name="montoFinal"
									class="form-control"
									value="<%=request.getAttribute("montoFinal") != null ? request.getAttribute("montoFinal") : ""%>">
							</div>
							<div class="d-flex flex-column align-items-center w-100">
								<button type="submit" onclick="return confirmarEnvio();"
									id="btnSolicitar" name="btnSolicitar"
									class="btn btn-success text-dark mt-4">
									<strong>Solicitar</strong>
								</button>
							</div>
						</form>
					</div>
					<br>
					<div class="d-flex justify-content-end w-100 mt-4">
						<a href="MenuCliente.jsp"
							class="btn btn-outline-success text-dark "><strong>
								Volver al menú</strong> </a>
					</div>
				</div>
			</div>
		</div>
		<div class="col-1 bgRight pt-5"></div>
	</div>
	<jsp:include page="Footer.jsp" />

</body>
</html>