<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registro de Cliente</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            display: flex;
            flex-direction: column;
            min-height: 100vh;
            margin: 0;
        }

        .content {
            flex: 1;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 20px;
        }

        .form-container {
            width: 100%;
            max-width: 600px;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        h2 {
            text-align: center;
            color: #333;
        }

        .form-grid {
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 15px;
        }

        .form-group {
            display: flex;
            flex-direction: column;
        }

        .form-group label {
            color: #555;
            font-weight: bold;
            margin-bottom: 5px;
        }

        .form-group input {
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 1rem;
        }

        .form-group.full-width {
            grid-column: span 2;
        }

        .submit-btn {
            width: 100%;
            padding: 10px;
            background-color: #4CAF50;
            color: white;
            font-size: 1rem;
            font-weight: bold;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            margin-top: 15px;
        }

        .submit-btn:hover {
            background-color: #45a049;
        }

        footer {
            width: 100%;
            background-color: #333;
            color: white;
            text-align: center;
            padding: 10px 0;
        }
    </style>
</head>
<body>

<jsp:include page="NavBar.jsp" />

<div class="content">
    <div class="form-container">
        <h2>Registro de Cliente</h2>
        <form>
            <div class="form-grid">
                <div class="form-group">
                    <label for="dni">DNI</label>
                    <input type="text" id="dni" name="dni" required>
                </div>
                <div class="form-group">
                    <label for="cuil">CUIL</label>
                    <input type="text" id="cuil" name="cuil" required>
                </div>
                <div class="form-group">
                    <label for="nombre">Nombre</label>
                    <input type="text" id="nombre" name="nombre" required>
                </div>
                <div class="form-group">
                    <label for="apellido">Apellido</label>
                    <input type="text" id="apellido" name="apellido" required>
                </div>
                <div class="form-group">
                    <label for="sexo">Sexo</label>
                    <input type="text" id="sexo" name="sexo" required>
                </div>
                <div class="form-group">
                    <label for="nacionalidad">Nacionalidad</label>
                    <input type="text" id="nacionalidad" name="nacionalidad" required>
                </div>
                <div class="form-group">
                    <label for="fecha_nacimiento">Fecha de Nacimiento</label>
                    <input type="date" id="fecha_nacimiento" name="fecha_nacimiento" required>
                </div>
                <div class="form-group">
                    <label for="direccion">Dirección</label>
                    <input type="text" id="direccion" name="direccion" required>
                </div>
                <div class="form-group">
                    <label for="localidad">Localidad</label>
                    <input type="text" id="localidad" name="localidad" required>
                </div>
                <div class="form-group">
                    <label for="provincia">Provincia</label>
                    <input type="text" id="provincia" name="provincia" required>
                </div>
                <div class="form-group full-width">
                    <label for="email">Correo Electrónico</label>
                    <input type="email" id="email" name="email" required>
                </div>
                <div class="form-group full-width">
                    <label for="telefono">Teléfono</label>
                    <input type="tel" id="telefono" name="telefono" required>
                </div>
                <div class="form-group">
                    <label for="usuario">Usuario</label>
                    <input type="text" id="usuario" name="usuario" required>
                </div>
                <div class="form-group">
                    <label for="contrasena">Contraseña</label>
                    <input type="password" id="contrasena" name="contrasena" required>
                </div>
            </div>
            <button type="submit" class="submit-btn">Registrarse</button>
        </form>
    </div>
</div>

<jsp:include page="Footer.jsp" />

</body>
</html>

