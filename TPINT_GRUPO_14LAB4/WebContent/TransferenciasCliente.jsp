<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Realizar Transferencia</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .content-container {
            margin-top: 100px;
            max-width: 800px;
        }
        .hidden {
            display: none;
        }
    </style>
</head>
<body>

    <!-- Header -->
    <jsp:include page="NavBar.jsp" />
    
    <div class="container content-container">
        <div class="d-flex flex-column align-items-center">
            
            <h2 class="mb-4">Realizar Transferencia</h2>

            <div class="card w-100">
                <div class="card-body">
                    <form action="ServletTransferencia" method="post">
                        
                        <div class="form-group">
                            <label>Tipo de Transferencia</label>
                            <div class="form-check">
                                <input type="radio" id="transferencia-propia" name="tipoTransferencia" class="form-check-input" value="propia" checked onclick="toggleTransferencia()">
                                <label for="transferencia-propia" class="form-check-label">Cuenta Propia</label>
                            </div>
                            <div class="form-check">
                                <input type="radio" id="transferencia-tercero" name="tipoTransferencia" class="form-check-input" value="tercero" onclick="toggleTransferencia()">
                                <label for="transferencia-tercero" class="form-check-label">A un Tercero</label>
                            </div>
                        </div>

                        <div id="cuenta-propia-fields" class="form-group">
                            <label for="cuenta-propia">Cuenta Destino (Propia)</label>
                            <select id="cuenta-propia" name="cuentaDestinoPropia" class="form-control">
                                <option value="">Seleccione una cuenta</option>
                                <option value="1234">Cuenta Corriente - 1234</option>
                                <option value="5678">Cuenta Ahorro - 5678</option>
                            </select>
                        </div>

                        <div id="tercero-fields" class="hidden">
                            <div class="form-group">
                                <label for="cuenta-destino">Numero de Cuenta Destino</label>
                                <input type="text" id="cuenta-destino" name="cuentaDestinoTercero" class="form-control" placeholder="Ingrese el numero de cuenta">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="monto">Monto a Transferir</label>
                            <input type="number" id="monto" name="monto" class="form-control" placeholder="Ingrese el monto" required>
                            <small class="form-text text-muted">Saldo disponible: $50,000</small>
                        </div>

                        <div class="form-group">
                            <label for="concepto">Concepto (opcional)</label>
                            <input type="text" id="concepto" name="concepto" class="form-control" placeholder="Motivo de la transferencia">
                        </div>

                        <div class="text-right">
                            <button type="submit" class="text-white bg-purple-500 border-0 py-2 px-8 focus:outline-none hover:bg-purple-600 rounded text-lg btn btn-success">Confirmar Transferencia</button>
                            <a href="CuentasClientes.jsp" class="btn btn-secondary">Cancelar</a>
                        </div>
                    </form>
                </div>
            </div>
            
        </div>
    </div>

    <jsp:include page="Footer.jsp" />

    <script>
        function toggleTransferencia() {
            const isPropia = document.getElementById("transferencia-propia").checked;
            document.getElementById("cuenta-propia-fields").classList.toggle("hidden", !isPropia);
            document.getElementById("tercero-fields").classList.toggle("hidden", isPropia);
        }
    </script>

</body>
</html>
