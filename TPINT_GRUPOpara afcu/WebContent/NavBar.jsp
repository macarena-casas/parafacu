<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String usuario = null;
    Integer tipoUsuario = null; // Se declara tipoUsuario como Integer
    if (session != null) {
        Object userIdObj = session.getAttribute("userId");
        if (userIdObj != null) {
            usuario = userIdObj.toString();  
        }
        tipoUsuario = (Integer) session.getAttribute("tipo_usuario_id"); // Retenido como Integer
    }
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Emerald Bank</title>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
<style>
#menu-toggle1:checked+label+#menu {
    display: block;
    z-index: 1000;
    opacity: 1;
}

#menu-toggle2:checked+label+#menu {
    display: block;
    z-index: 1000;
    opacity: 1;
}
</style>
</head>
<body>
    <header class="text-secondary fixed-top w-100 bg-rgba(255, 255, 255, 0.5) shadow">
        <div class="container d-flex flex-wrap p-3 flex-column flex-md-row align-items-center">
            <nav class="d-flex flex-lg-grow-1 d-flex flex-wrap align-items-center text-base ml-md-auto">
                <input type="hidden" class="mr-3 hover:text-success">
            </nav>

            <a class="d-flex order-first order-lg-0 flex-grow-1 flex-lg-grow-0 title-font font-weight-medium align-items-center text-success justify-content-center mb-3 mb-md-0">
                <img src="imagenes/logo.jpeg" alt="Green Emerald" class="img" style="width: 60px; height: 60px; border-radius: 50%;"> 
                <span class="ml-3 text-black h3"><strong>Emerald-Bank</strong></span>
            </a>

            <div class="flex-lg-grow-1 d-flex justify-content-end ml-3 ml-lg-0">
                <% 
                    if (usuario == null) {                  
                %>
                    <a href="Login.jsp" class="btn btn-outline-success border-0 py-1 px-3 hover:bg-green rounded text-base mt-3 mt-md-0"><strong>Log in</strong></a>
                <% 
                    } else {  
                %>
                    <a href="Home.jsp" class="btn btn-outline-success border-0 py-1 px-3 hover:bg-green rounded text-base mt-3 mt-md-0"><strong>Home</strong></a>
                <% 
                    }
                %>
            </div>

            <!-- Menú desplegable -->
            <div class="relative d-inline-block text-left ml-3">
                <input type="checkbox" class="d-none" id="menu-toggle1" />
                <label for="menu-toggle1" class="btn btn-outline-success shadow-sm px-2 py-1 text-sm font-weight-medium text-dark hover-bg-light-green cursor-pointer">
                    <strong> Menú<i class="bi bi-arrow-down-short"></i></strong>
                </label>
                <div class="dropdown-menu dropdown-menu-right mt-2 w-30 rounded shadow-lg bg-white" id="menu">
                    <div class="py-1" role="none">
                        <a href="Home.jsp" class="dropdown-item text-dark">Inicio</a>

                        <% if (tipoUsuario != null && tipoUsuario == 1) { %> <!-- Si es admin -->
                            <a href="MenuAdmin.jsp" class="dropdown-item border-bottom border-success text-dark">Menú Admin</a>
                            <a href="AgregarCliente.jsp" class="dropdown-item text-dark">Agregar Cliente</a>
                            <a href="ModificarCliente.jsp" class="dropdown-item text-dark">Administrar clientes</a>
                            <a href="AgregarCuenta.jsp" class="dropdown-item text-dark">Agregar Cuenta</a>
                            <a href="ServletAdminCuentas?btnAdminCuentas" class="dropdown-item text-dark">Administrar cuentas</a>
                            <a href="ListarSolicitudPrestamos.jsp" class="dropdown-item text-dark">Préstamos</a>
                            <a href="Reportes.jsp" class="dropdown-item text-dark">Reportes</a>
                        <% } else if (tipoUsuario != null && tipoUsuario == 2) { %> <!-- Si es cliente -->
                            <a href="SolicitudPrestamo.jsp" class="dropdown-item text-dark">Solicitar Préstamo</a>
                            <a href="VerCuentas.jsp" class="dropdown-item text-dark">Ver Cuentas</a>
                        <% } %>

                        <form action="ServletSesion" method="post">
                            <button type="submit" name="btnCerrarSesion" value="true" class="dropdown-item text-dark">Cerrar Sesión</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </header>

    <script>
        document.addEventListener("DOMContentLoaded", function() {
            var checkbox = document.getElementById("menu-toggle1");
            var menu = document.getElementById("menu");

            checkbox.addEventListener("change", function() {
                if (this.checked) {
                    menu.classList.add("show");
                } else {
                    menu.classList.remove("show");
                }
            });
        });
    </script>
    <script>
        document.addEventListener("DOMContentLoaded", function() {
            var checkbox = document.getElementById("menu-toggle2");
            var menu = document.getElementById("menu");

            checkbox.addEventListener("change", function() {
                if (this.checked) {
                    menu.classList.add("show");
                } else {
                    menu.classList.remove("show");
                }
            });
        });
    </script>
</body>
</html>
