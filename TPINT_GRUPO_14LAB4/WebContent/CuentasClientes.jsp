<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cuenta Cliente</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .bgLeft {
            background: linear-gradient(45deg, mediumseagreen, seagreen, forestgreen, forestgreen, seagreen, mediumseagreen);
            background-size: cover;
        }
        .profile-img {
            width: 100px;
            height: 100px;
            border-radius: 50%;
            background-color: #dcdcdc;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 40px;
            color: #fff;
        }
        .account-info {
            font-size: 1.2rem;
        }
       
        .content-container {
            margin-top: 100px; 
            max-width: 800px; 
        }
    </style>
</head>
<body>

    <!-- Header -->
    <jsp:include page="NavBar.jsp" />

    <div class="container content-container">
        <div class="d-flex flex-column align-items-center">

            <div class="row w-100">
                <div class="col-md-6 d-flex align-items-start mb-5">
                    <div class="profile-img text-center">
                        <span>👤</span>
                    </div>

                    <div class="ml-4 account-info">
                        <h3>Nombre de Cuenta</h3>
                        <p><strong>Numero de Cuenta:</strong> 1234-5678-9101</p>
                        <p><strong>Tipo de Cuenta:</strong> Ahorros</p>
                        <p><strong>Saldo Actual:</strong> $50,000</p>

                        <div class="mt-4">
                            <button class="text-white bg-purple-500 border-0 py-2 px-8 focus:outline-none hover:bg-purple-600 rounded text-lg btn btn-success">Transferencia</button>
                            <button class="text-white bg-purple-500 border-0 py-2 px-8 focus:outline-none hover:bg-purple-600 rounded text-lg btn btn-success">Solicitar Prestamo</button>
                        </div>
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="card">
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
                                </tbody>
                            </table>
                            
                        </div>
                        <div class="card-footer text-right">
						    <a href="ListarMovimientos.jsp" class="text-white bg-purple-500 border-0 py-2 px-8 focus:outline-none hover:bg-purple-600 rounded text-lg btn btn-success">
						        Listar Movimientos
						    </a>
						</div>
                    </div>
                </div>
            </div>
            
        </div>
    </div>

    <jsp:include page="Footer.jsp" />

</body>
</html>
