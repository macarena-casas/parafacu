<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="entidad.Prestamo"%>
<%@ page import="entidad.Cuota"%>
<%@ page import="entidad.Cuenta"%>


<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Pago Préstamos</title>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<style>
.bgLeft {
	background: linear-gradient(45deg, lightgreen, limegreen, darkgreen, limegreen,
		mediumseagreen);
	background-size: cover;
}

.bgRight {
	background: linear-gradient(45deg, lightgreen, limegreen, darkgreen, limegreen,
		lightgreen);
	background-size: cover
}
</style>
</head>
<body>
	<jsp:include page="NavBar.jsp" />
	<div class="d-flex">
		<div class="col-1 bgLeft pt-5"></div>
		<div class="col-10 p-4 bg-white bg-opacity-80 pt-5">
			<div class="bg-white pt-5">
				<div class="container py-4"
					style="width: 100%; overflow-y: auto; min-height: 600px;">
					<section class="text-gray-600 body-font">
						<div class="container px-5 py-24 mx-auto">
							<div class="d-flex flex-column text-center w-100 mb-6">
								<h1 class="text-center font-weight-medium mb-2 text-dark">Pago
									Préstamos</h1>
								<%
									//if (prestamos == null || prestamos.isEmpty()) {
								%>
								<h1>No hay pagos disponibles</h1>
								<%
									//} else {
								%>
								<p class="mx-auto leading-relaxed text-base">Ingrese en el
									préstamo que desea pagar.</p>
							</div>
							<div class="w-75 mx-auto overflow-auto">
								<div class="shadow-md">
									<%
										//for (Prestamo prestamo : prestamos) {
									%>
									<div class="border-top border-gray-200">
										<button
											class="w-100 px-4 py-2 text-left focus:outline-none focus:bg-gray-200"
											onclick="toggleSection('section<%//=prestamo.getPrestamoId()%>')">
											Id Préstamo:
											<%
											//=prestamo.getPrestamoId()
										%>
										</button>
										<div id="section<%//=prestamo.getPrestamoId()%>"
											class="d-none px-4 py-2">
											<section class="text-gray-600 body-font">
												<div class="container px-5 py-5 mx-auto">
													<form id="prestamoForm"
														class="d-flex flex-column align-items-center w-100"
														action="ServletPagoPrestamos" method="post"
														onsubmit="return confirmacion();">
														<div class="mb-4 w-100">
															<label class="form-label" for="CuentaOrigen">Cuenta
																a debitar</label>
															<div class="position-relative">
																<select id="CuentaOrigen" name="CuentaOrigen"
																	class="form-select">
																	<%
																		//if (cuentasPorCliente != null && !cuentasPorCliente.isEmpty()) {
																	%>
																	<%
																		//for (Cuenta cuenta : cuentasPorCliente) {
																	%>
																	<option value="<%//=cuenta.getNumeroCuenta()%>">N°
																		<%
																		//=cuenta.getNumeroCuenta()
																	%>
																	</option>
																	<%
																		//}
																	%>
																	<%
																		//} else {
																	%>
																	<option value="">No tiene cuentas disponibles</option>
																	<%
																		//}
																	%>
																</select>
																<div
																	class="position-absolute top-50 end-0 translate-middle-y pe-2 text-gray-700">

																</div>
															</div>
														</div>
														<div class="d-flex flex-column text-center w-100 mb-10">
															<p class="mx-auto leading-relaxed text-base">Seleccione
																la cuota que desea pagar:</p>
														</div>
														<div class="w-75 mx-auto overflow-auto">
															<table class="table table-striped">
																<thead>
																	<tr>
																		<th>ID cuota</th>
																		<th>N° de Cuota</th>
																		<th>Fecha Vencimiento</th>
																		<th>Importe</th>
																		<th></th>
																	</tr>
																</thead>
																<tbody>
																	<%
																		//for (Cuota cuota : prestamo.getCuotas()) {
																	%>
																	<tr>
																		<td>
																			<%
																				//=cuota.getCuotaId()
																			%>
																		</td>
																		<td>
																			<%
																				//=cuota.getNumeroCuota()
																			%>
																		</td>
																		<td>
																			<%
																				//=cuota.getFechaVencimiento()
																			%>
																		</td>
																		<td>$<%
																			//=cuota.getImporte()
																		%></td>
																		<td class="text-center"><input type="hidden"
																			name="nroCuota" id="nroCuota"
																			value="<%//=cuota.getNumeroCuota()%>"> <input
																			required name="idCuota" id="idCuota" type="radio"
																			value="<%//=cuota.getCuotaId()%>"></td>
																	</tr>
																	<%
																		//}
																	%>
																</tbody>
															</table>
														</div>
														<input type="hidden" name="prestamoId" id="prestamoId"
															value="<%//=prestamo.getPrestamoId()%>">
														<div class="d-flex pl-4 mt-4 w-75 mx-auto">
															<button type="submit" name="btnPagarCuota"
																class="btn btn-success text-dark ml-auto">
																<strong>Pagar</strong>
															</button>
														</div>
													</form>
												</div>
											</section>
										</div>
									</div>
									<%
										//	}
									%>
								</div>
								<%
									//}
								%>
							</div>
						</div>
					</section>
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
	<script>
		function toggleSection(sectionId) {
			const section = document.getElementById(sectionId);
			section.classList.toggle('d-none');
		}
		function confirmacion() {
			return confirm("¿Está seguro que desea pagar esta cuota?");
		}
	</script>
</body>
</html>