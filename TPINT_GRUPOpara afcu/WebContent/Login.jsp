<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Log in</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.10.0/font/bootstrap-icons.min.css" rel="stylesheet">
</head>
<body>

<jsp:include page="NavBar.jsp" />

<div class="bg-white pt-5">
    <div class="content-background min-vh-100 d-flex align-items-center justify-content-center">
        <section class="text-gray-900 body-font">
            <div class="container px-5 py-5 mx-auto d-flex flex-wrap align-items-center" style="margin-top: 15%;">
                <div class="row w-100">
                    <div class="col-lg-6 col-md-6 col-12 d-flex justify-content-start align-items-start">
                        <div class="text-left">
                            <h1 class="font-weight-medium text-3xl text-dark">
                                ¡Hola! <br> Te damos la bienvenida
                            </h1>
                            <br>
                            <img src="imagenes/fondo2.jpg" alt="Bienvenida" class="object-cover object-left rounded"
                                 style="display: block; margin: 0; min-width: 250px; min-height: 360px; max-width: 340px; max-height: 380px;">
                        </div>
                    </div>

                    <div class="col-lg-6 col-md-6 col-12 d-flex justify-content-center align-items-center"
                         style="background-image: url('imagenes/fondo.png'); background-size: cover; background-position: center; max-width: 350px; max-height: 600px;">
                        <div class="rounded-lg p-4 text-center">
                            <h2 class="text-dark text-lg font-weight-medium mb-4">
                                <strong>Log in</strong>
                            </h2>

                          
                            <% 
                                String error = request.getParameter("error");
                                if ("1".equals(error)) {
                            %>
                                <div class="alert alert-danger" role="alert">
                                    Usuario o contraseña incorrectos. Intenta nuevamente.
                                </div>
                            <% 
                                }
                            %>

                            <form action="ServletSesion" method="post">
                                <div class="form-group mb-3">
                                    <label for="username" class="text-sm text-gray-900">Usuario</label>
                                    <input type="text" id="username" name="username"
                                           class="form-control bg-white rounded border border-gray-300 focus:border-primary focus:ring-2 focus:ring-primary text-base outline-none text-dark py-1 px-3 leading-8 transition-colors duration-200 ease-in-out"
                                           required>
                                </div>
                                <div class="form-group mb-3">
                                    <label for="password" class="text-sm text-gray-900">Contraseña</label>
                                    <input type="password" id="password" name="password"
                                           class="form-control bg-white rounded border border-gray-300 focus:border-primary focus:ring-2 focus:ring-primary text-base outline-none text-dark py-1 px-3 leading-8 transition-colors duration-200 ease-in-out"
                                           required>
                                    <div class="form-check mt-2">
                                        <input type="checkbox" id="show-password" class="form-check-input">
                                        <label for="show-password" class="form-check-label text-sm text-black">Ver contraseña</label>
                                    </div>
                                </div>
                                <button type="submit" name="btnIngresar"
                                        class="btn btn-outline-success text-gray-600 py-2 px-4 rounded text-lg">
                                    <strong>Ingresar</strong>
                                </button>
                            </form>

                            <p class="text-xs text-gray-700 mt-3">No compartas ningún dato personal. Nadie del banco lo va a solicitar.</p>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>

    <script>
        document.addEventListener("DOMContentLoaded", function () {
            var checkbox = document.getElementById("show-password");
            var passwordField = document.getElementById("password");

            checkbox.addEventListener("change", function () {
                passwordField.type = this.checked ? "text" : "password";
            });
        });
    </script>

</div>

<jsp:include page="Footer.jsp" />

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
