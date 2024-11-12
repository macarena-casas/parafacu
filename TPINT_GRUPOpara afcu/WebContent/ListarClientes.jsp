<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>



<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Administrador Clientes</title>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.11.4/css/jquery.dataTables.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" charset="utf8"
	src="https://cdn.datatables.net/1.11.4/js/jquery.dataTables.js"></script>
<link rel="icon" type="image/png" href="Images/logo.png">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.8.1/font/bootstrap-icons.min.css">
</head>
<body>
	<jsp:include page="NavBar.jsp" />
	<div class="bg-white pt-5">
	<br><br>
		<div class="container"
			style="width: 80%; overflow-y: auto; min-height: 600px;">
			<h2 class="text-center text-dark mt-3">
				<strong>Administrador de clientes</strong>
			</h2>
			<br> <br>

			<table id="tablaClientes" class="table table-striped table-bordered"
				style="border: 2px; color: solid green;">
				<thead>
					<tr>
						<th>Nombre</th>
						<th>Apellido</th>
						<th>DNI</th>
						<th>Sexo</th>
						<th>Nacionalidad</th>
						<th>Detalle</th>
						<th>Modificar</th>
						<th>Eliminar</th>
					</tr>
				</thead>
				<tbody>

					<tr>
						<td class="text-center"></td>
						<td class="text-center"></td>
						<td class="text-center"></td>
						<td class="text-center"></td>
						<td class="text-center"></td>
						<td>
							<form action="ServletAdminCliente" method="get">
								<center>
									<input type="hidden" name="dni" value="">
									<button type="submit" name="btnDetalle" value="detalle"
										class="btn btn-outline-success">
										<i class="bi bi-plus-lg" style="color: black;"></i>
									</button>
							</form>
							</center>
						</td>
						<td>
							<form action="ServletAdminCliente" method="get">
								<center>
									<input type="hidden" name="dni" value="">
									<button type="submit" name="btnModificar" value="modificar"
										class="btn btn-outline-warning">
										<i class="bi bi-pencil-square" style="color: black;"></i>
									</button>
							</form>
							</center>
						</td>
						<td>
							<form action="ServletAdminCliente" method="get" onsubmit="">
								<center>
									<input type="hidden" name="dni" value="">
									<button type="submit" name="btnEliminar" value="eliminar"
										class="btn btn-outline-danger">
										<i class="bi bi-trash3" style="color: black;"></i>


									</button>
							</form>
							</center>
						</td>
					</tr>

				</tbody>
			</table>
			<br>
			<div class="d-flex justify-content-end w-100 mt-4">
				<a href="MenuAdmin.jsp" class="btn btn-success"
					style="color: black;"><strong>Volver al menú</strong></a>
			</div>
		</div>
	</div>
	<jsp:include page="Footer.jsp" />
</body>
</html>