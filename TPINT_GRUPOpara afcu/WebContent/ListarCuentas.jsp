<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Administrador de Cuentas</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.4/css/jquery.dataTables.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.11.4/js/jquery.dataTables.js"></script>
    <link rel="icon" type="image/png" href="Images/logo.png">
</head>
<body>
    <jsp:include page="NavBar.jsp" />
    <div class="bg-white pt-5">
        <div class="container" style="width: 80%; overflow-y: auto; min-height: 600px;margin-top:10%">
            <h2 class="text-center text-dark mt-3">Listado de Cuentas</h2>
            <br><br>

            <table id="tablaCuentas" class="table table-striped table-bordered">
                <thead>
                    <tr>
                        <th>Cliente Asignado</th>
                        <th>Tipo de Cuenta</th>
                        <th>Nro de Cuenta</th>
                        <th>CBU</th>
                        <th>Saldo</th>
                        <th>Detalle</th>
                        <th>Modificar</th>
                        <th>Eliminar</th>
                    </tr>
                </thead>
                <tbody>
                    <!-- Aquí se agregarán dinámicamente las filas de cuentas desde el servidor -->
                    <tr>
                        <td class="text-center">Nombre del Cliente</td>
                        <td class="text-center">Tipo de Cuenta</td>
                        <td class="text-center">123456789</td>
                        <td class="text-center">0012345678901234567890</td>
                        <td class="text-center">$10,000</td>
                        <td>
                            <form action="ServletAdminCuenta" method="get">
                                <input type="hidden" name="cuentaId" value="123456789">
                                <button type="submit" name="btnDetalle" value="detalle" class="btn btn-success" style="color:black;">Detalle</button>
                            </form>
                        </td>
                        <td>
                            <form action="ServletAdminCuenta" method="get">
                                <input type="hidden" name="cuentaId" value="123456789">
                                <button type="submit" name="btnModificar" value="modificar" class="btn btn-warning btn-block" style="color:black;">Modificar</button>
                            </form>
                        </td>
                        <td>
                            <form action="ServletAdminCuenta" method="get" onsubmit="return confirm('¿Está seguro de que desea eliminar esta cuenta?');">
                                <input type="hidden" name="cuentaId" value="123456789">
                                <button type="submit" name="btnEliminar" value="eliminar" class="btn btn-danger btn-block" style="color:black;">Eliminar</button>
                            </form>
                        </td>
                    </tr>
                </tbody>
            </table>
            <br>
            <div class="d-flex justify-content-end w-100 mt-4">
                <a href="MenuAdmin.jsp" class="btn btn-success">Volver al menú</a>
            </div>
        </div>
    </div>
            <jsp:include page="Footer.jsp" />

</body>
</html>