<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .bgLeft {
            background: linear-gradient(45deg, mediumseagreen, seagreen, forestgreen, forestgreen, seagreen, mediumseagreen);
            background-size: cover;
        }
        .bgRight {
            background: linear-gradient(45deg, mediumseagreen, seagreen, forestgreen, forestgreen, seagreen, mediumseagreen);
            background-size: cover;
        }
    </style>
</head>
<body>
    <br><br>
    <title>Detalle Del cliente</title>

    <jsp:include page="NavBar.jsp" />
    <div class="d-flex">
        <div class="w-10 bgLeft pt-5"></div>
        <div class="w-90 p-4 bg-white bg-opacity-80 pt-5">
            <div class="container mx-auto px-4 py-8">
                <div class="w-66 mx-auto">
                    <center>
                        <h2 class="text-3xl font-medium title-font mb-2 text-gray-900">Detalle del cliente</h2>
                    </center>
                    <br>
                    <form action="ServletAdminCliente" method="post" onsubmit="return validarFormulario(event);">
                        <div class="row">
                            <center>
                                <div class="col-12 border-bottom pb-4">
                                    <h3 class="text-lg font-medium title-font mb-2 text-gray-700">Datos Personales</h3>
                                </div>
                            </center>
                            <div class="col-md-6 mb-4">
                                <label class="form-label" for="nombre">Nombre:</label>
                                <input type="text" id="nombre" name="nombre" maxlength="100" class="form-control" required disabled>
                            </div>
                            <div class="col-md-6 mb-4">
                                <label class="form-label" for="apellido">Apellido:</label>
                                <input type="text" id="apellido" name="apellido" maxlength="100" class="form-control" required disabled>
                            </div>
                            <div class="col-md-6 mb-4">
                                <label class="form-label" for="dni">DNI:</label>
                                <input type="number" id="dni" name="dni" class="form-control" required disabled>
                            </div>
                            <div class="col-md-6 mb-4">
                                <label class="form-label" for="cuil">CUIL:</label>
                                <input type="number" id="cuil" name="cuil" maxlength="13" class="form-control" required disabled>
                            </div>
                            <div class="col-md-6 mb-4">
                                <label class="form-label" for="sexo">Sexo:</label>
                                <select id="sexo" name="sexo" class="form-control" disabled>
                                    <option value="M">M</option>
                                    <option value="F">F</option>
                                    <option value="X">X</option>
                                </select>
                            </div>
                            <div class="col-md-6 mb-4">
                                <label class="form-label" for="nacionalidad1">Nacionalidad:</label>
                                <select id="nacionalidad1" name="nacionalidad1" class="form-control" required disabled>
                                    
                                </select>
                            </div>
                            <div class="col-md-6 mb-4">
                                <label class="form-label" for="fecha-nacimiento">Fecha de Nacimiento:</label>
                                <input type="date" id="fecha-nacimiento" name="fechanacimiento" max="2006-01-01" min="1930-01-01" class="form-control" required disabled>
                            </div>
                            <div class="col-md-6 mb-4">
                                <label class="form-label" for="direccion">Dirección:</label>
                                <input type="text" id="direccion" name="direccion" maxlength="100" class="form-control" required disabled>
                            </div>
                            <div class="col-md-6 mb-4">
                                <label class="form-label" for="localidad">Localidad:</label>
                                <select id="localidad" name="localidad" class="form-control" required disabled>
                                    
                                </select>
                            </div>
                            <div class="col-md-6 mb-4">
                                <label class="form-label" for="provincia">Provincia:</label>
                                <select id="provincia" name="provincia" class="form-control" required disabled>
                                    
                                </select>
                            </div>
                            <div class="col-md-6 mb-4">
                                <label class="form-label" for="telefono">Teléfono:</label>
                                <input type="number" id="telefono" name="telefono" class="form-control" required disabled>
                            </div>
                            <div class="col-md-6 mb-4">
                                <label class="form-label" for="email">Email:</label>
                                <input type="email" id="email" name="email" maxlength="100" class="form-control" required disabled>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <jsp:include page="Footer.jsp" />
</body>
</html>
