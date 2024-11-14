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
                    <form>
                        <div class="row">
                            <center>
                                <div class="col-12 border-bottom pb-4">
                                    <h3 class="text-lg font-medium title-font mb-2 text-gray-700">Datos Personales</h3>
                                </div>
                            </center>
                            <div class="col-md-6 mb-4">
                                <label class="form-label" for="nombre">Nombre:</label>
                                <input type="text" id="nombre" name="nombre" value="<%= request.getAttribute("nombreCliente") %>" class="form-control" disabled>
                            </div>
                            <div class="col-md-6 mb-4">
                                <label class="form-label" for="apellido">Apellido:</label>
                                <input type="text" id="apellido" name="apellido" value="<%= request.getAttribute("apellidoCliente") %>" class="form-control" disabled>
                            </div>
                            <div class="col-md-6 mb-4">
                                <label class="form-label" for="sexo">Sexo:</label>
                                <input type="text" id="sexo" name="sexo" value="<%= request.getAttribute("sexo") %>" class="form-control" disabled>
                            </div>
                            <div class="col-md-6 mb-4">
                                <label class="form-label" for="nacionalidad">Nacionalidad:</label>
                                <input type="text" id="nacionalidad" name="nacionalidad" value="<%= request.getAttribute("nacionalidad") %>" class="form-control" disabled>
                            </div>
                            <div class="col-md-6 mb-4">
                                <label class="form-label" for="fechanacimiento">Fecha de Nacimiento:</label>
                                <input type="text" id="fechanacimiento" name="fechanacimiento" value="<%= request.getAttribute("fechanacimiento") %>" class="form-control" disabled>
                            </div>
                            <div class="col-md-6 mb-4">
                                <label class="form-label" for="direccion">Dirección:</label>
                                <input type="text" id="direccion" name="direccion" value="<%= request.getAttribute("direccion") %>" class="form-control" disabled>
                            </div>
                            <div class="col-md-6 mb-4">
                                <label class="form-label" for="correo">Correo:</label>
                                <input type="email" id="correo" name="correo" value="<%= request.getAttribute("correo") %>" class="form-control" disabled>
                            </div>
                            <div class="col-md-6 mb-4">
                                <label class="form-label" for="telefono">Teléfono:</label>
                                <input type="text" id="telefono" name="telefono" value="<%= request.getAttribute("telefono") %>" class="form-control" disabled>
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
