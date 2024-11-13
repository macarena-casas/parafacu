<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%
	String usuario = null;
	String tipoUsuario = null;
	if (session != null && session.getAttribute("usuario") != null
			&& session.getAttribute("tipoUsuario") != null) {
		usuario = (String) session.getAttribute("usuario");
		tipoUsuario = (String) session.getAttribute("tipoUsuario");
	}
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">

<title>Menú de cliente</title>
<style>
.content-background {
	background-size: cover;
}

.menu-container {
	background-image: url('imagenes/fondo.png');
	padding: 2rem;
	border-radius: 0.5rem;
	margin-top: 20%;
}
</style>
</head>
<body>

	<jsp:include page="NavBar.jsp" />
	<!--
    <%/*  
									       String respuesta = null;
									       if(session != null && session.getAttribute("respuesta") != null){
									respuesta = (String)session.getAttribute("respuesta");
									session.removeAttribute("respuesta");
									  */%>
    <script> 
        alert('<%/*= No hay usuario logueado*/%>');
    </script>   
    <%/*
									        respuesta = null;
									    }
									  */%>
    -->

	
				<div
				class="content-background min-vh-100 d-flex align-items-center justify-content-center">

				<section
					class="text-gray-600 body-font min-vh-100 d-flex align-items-center justify-content-center w-80" style="min-width:550px;">
				<div
				class="container px-5 py-5 mx-auto d-flex flex-column align-items-center">
						<div class="menu-container w-100 md:w-50 lg:w-33">
							<h1
								class="title-font font-medium text-3xl text-gray-900 text-center">
				
							Hola
							<%=usuario%>
							!
						</h1>
					
						<p class="leading-relaxed mt-4 text-center"><strong>    Bienvenido/a al
							menú de Emerald   </strong></p>
						<br>
						
						<div class="button-container space-y-4">
							<form action="ServletCliente" method="get">
							<center>
								<div class="mb-3">
									<button type="submit" name="btnPerfil" value="true"
										class="btn btn-outline-success text-dark py-2 px-4 rounded text-lg w-50"><strong>Perfil</strong></button>
								</div>
								<div class="mb-3">
									<button type="submit" name="btnCuentas" value="true"
										class="btn btn-outline-success text-dark py-2 px-4 rounded text-lg w-50"><strong>Cuentas</strong></button>
								</div>
							</form>
							<form action="ServletTransferenciasClientes" method="get">
								<div class="mb-3">
									<button type="submit" name="btnTransferencias" value="true"
										class="btn btn-outline-success text-dark py-2 px-4 rounded text-lg w-50"><strong>Transferencias</strong></button>
								</div>
							</form>
							<form action="ServletPrestamosAdmin" method="get">
								<div class="mb-3">
									<button type="submit" name="btnSolicitarPrestamos" value="true"
										class="btn btn-outline-success text-dark py-2 px-2  rounded text-lg w-50"><strong>Solicitar
										préstamo</strong></button>
								</div>
							</form>
							<form action="ServletPrestamosPagos" method="get">
								<div class="mb-3">
									<button type="submit" name="btnPagoDePrestamos" value="true"
										class="btn btn-outline-success text-dark py-2 px-2  rounded text-lg w-50"><strong>Pago de
										préstamos</strong></button>
								</div>
							</form>
								</center>
						</div>
					</div>
				</div>
			</section>
		</div>
	
	<jsp:include page="Footer.jsp" />
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
