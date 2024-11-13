<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Perfil del Cliente</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
        }
        .profile-container {
            width: 100%;
            max-width: 600px;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            margin: 80px auto 40px auto; /* Ajuste de margen superior */
        }
        h2 {
            text-align: center;
            color: #333;
        }
        .section {
            margin-bottom: 20px;
        }
        .section h3 {
            color: #4CAF50;
            border-bottom: 2px solid #4CAF50;
            padding-bottom: 5px;
        }
        .info {
            display: flex;
            justify-content: space-between;
            margin-bottom: 10px;
        }
        .info label {
            font-weight: bold;
            color: #555;
        }
        .info span {
            color: #333;
        }
    </style>
</head>
<body>

<jsp:include page="NavBar.jsp" />

<div class="profile-container">
    <h2>Perfil del Cliente</h2>

    <!-- Información Básica -->
    <div class="section">
        <h3>Información Básica</h3>
        <div class="info">
            <label>DNI:</label>
            <span>12345678</span>
        </div>
        <div class="info">
            <label>Nombre:</label>
            <span>Juan Pérez</span>
        </div>
        <div class="info">
            <label>Sexo:</label>
            <span>Masculino</span>
        </div>
        <div class="info">
            <label>Fecha de Nacimiento:</label>
            <span>01/01/1980</span>
        </div>
    </div>

    <!-- Información de la Cuenta -->
    <div class="section">
        <h3>Información de la Cuenta</h3>
        <div class="info">
            <label>Número de Cuenta:</label>
            <span>1234-5678-9101</span>
        </div>
        <div class="info">
            <label>Tipo de Cuenta:</label>
            <span>Ahorros</span>
        </div>
        <div class="info">
            <label>Saldo Actual:</label>
            <span>$50,000</span>
        </div>
    </div>

    <!-- Información de Contacto -->
    <div class="section">
        <h3>Información de Contacto</h3>
        <div class="info">
            <label>Correo Electrónico:</label>
            <span>juan.perez@correo.com</span>
        </div>
        <div class="info">
            <label>Teléfono:</label>
            <span>+54 9 11 1234-5678</span>
        </div>
        <div class="info">
            <label>Dirección:</label>
            <span>Calle Falsa 123, Buenos Aires</span>
        </div>
    </div>
</div>

<jsp:include page="Footer.jsp" />

</body>
</html>