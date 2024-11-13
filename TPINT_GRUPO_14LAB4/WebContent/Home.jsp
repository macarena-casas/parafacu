<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Emerald-Bank</title>
    <style>
      
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f0f5f4;
        }
        .container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 20px;
        }
       
        .hero {
            background-color: #388e3c;
            color: #fff;
            padding: 60px 20px;
            text-align: center;
            margin-top: 70px;
        }
        .hero h1 {
            font-size: 2.5em;
            margin: 0;
        }
        .hero p {
            font-size: 1.2em;
            margin: 20px 0;
        }
        .hero button {
            background-color: #1b5e20;
            color: #fff;
            padding: 15px 30px;
            border: none;
            border-radius: 5px;
            font-size: 1em;
            cursor: pointer;
        }
        .hero button:hover {
            background-color: #145a17;
        }
   
        .services {
            display: flex;
            justify-content: space-around;
            margin: 40px 0;
            flex-wrap: wrap;
        }
        .service-item {
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            padding: 20px;
            text-align: center;
            width: 30%;
            margin: 10px;
        }
        .service-item h3 {
            color: #2e7d32;
        }
        .service-item p {
            color: #555;
            font-size: 0.95em;
            line-height: 1.5;
        }
        .service-item button {
            background-color: #2e7d32;
            color: #fff;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            margin-top: 10px;
        }
        .service-item button:hover {
            background-color: #1b5e20;
        }
    </style>
</head>

<body>
<jsp:include page="NavBar.jsp" />

 
    <section class="hero" style= "margin-top:10%;">
        <h1>Bienvenido a Emerald-Bank</h1>
        <p>Tu socio financiero de confianza.</p>
        
        <% 
            Integer userId = (Integer) session.getAttribute("userId");
            if (userId != null) { 
        %>
            <p>ID de Usuario: <strong><%= userId %></strong></p>
        <% 
            } else { 
        %>
            <p>Error: Usuario no logueado</p>
        <% 
            } 
        %>
        
        <button>Conoce más</button>
    </section>

   
    <div class="container">
        <section class="services">
            <div class="service-item">
                <h3>Cuentas Bancarias</h3>
                <p>Descubre nuestras cuentas de ahorro y corriente adaptadas a tus necesidades.</p>
                <button>Más información</button>
            </div>
            <div class="service-item">
                <h3>Préstamos</h3>
                <p>Ofrecemos préstamos personales, hipotecarios y de automóviles con tasas competitivas.</p>
                <button>Más información</button>
            </div>
            <div class="service-item">
                <h3>Transferencias</h3>
                <p>Envía tu dinero de forma segura y sin comisiones.</p>
                <button>Más información</button>
            </div>
        </section>
    </div>
    
    <jsp:include page="Footer.jsp" />
</body>
</html>
