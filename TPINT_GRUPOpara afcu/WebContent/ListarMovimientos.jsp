<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Movimientos de Cuenta</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .content-container {
            margin-top: 100px; 
            max-width: 800px;
        }
    </style>
</head>
<body>

    
    <jsp:include page="NavBar.jsp" />

    
    <div class="container content-container">
        <div class="d-flex flex-column align-items-center">
            
           
            <h2 class="mb-4">Movimientos de Cuenta</h2>
            
            
            <div class="w-100 mb-3">
                <form class="form-inline justify-content-center">
                    <div class="form-group mr-2">
                        <label for="fecha-desde" class="mr-2">Desde</label>
                        <input type="date" id="fecha-desde" name="fechaDesde" class="form-control">
                    </div>
                    <div class="form-group mr-2">
                        <label for="fecha-hasta" class="mr-2">Hasta</label>
                        <input type="date" id="fecha-hasta" name="fechaHasta" class="form-control">
                    </div>
                    <div class="form-group mr-2">
                        <label for="palabra-clave" class="mr-2">Palabra clave</label>
                        <input type="text" id="palabra-clave" name="palabraClave" class="form-control" placeholder="Buscar...">
                    </div>
                    <button type="submit" class="text-white bg-purple-500 border-0 py-2 px-8 focus:outline-none hover:bg-purple-600 rounded text-lg btn btn-success">Buscar</button>
                </form>
            </div>

            
            <div class="card w-100">
                <div class="card-header bg-success text-white">
                    <h4>Historial de Movimientos</h4>
                </div>
                <div class="card-body">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>Fecha</th>
                                <th>Descripcion</th>
                                <th>Monto</th>
                                <th>Saldo</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>01/11/2024</td>
                                <td>Deposito</td>
                                <td>$1,000</td>
                                <td>$51,000</td>
                            </tr>
                            <tr>
                                <td>02/11/2024</td>
                                <td>Retiro</td>
                                <td>-$500</td>
                                <td>$50,500</td>
                            </tr>
                            <tr>
                                <td colspan="4" class="text-center text-muted">No hay más movimientos</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>

            <div class="w-100 text-right mt-3">
                <a href="CuentasClientes.jsp" class="text-white bg-purple-500 border-0 py-2 px-8 focus:outline-none hover:bg-purple-600 rounded text-lg btn btn-success">Volver</a>
            </div>
            
        </div>
    </div>

    <!-- Footer -->
    <jsp:include page="Footer.jsp" />

</body>
</html>
