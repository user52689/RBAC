<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Iniciar Sesión</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 50px; }
        .login-container { width: 300px; margin: auto; }
        .error { color: red; }
        input[type="text"], input[type="password"] { width: 100%; padding: 8px; margin: 5px 0; }
        input[type="submit"] { padding: 10px; background-color: #4CAF50; color: white; border: none; cursor: pointer; }
    </style>
</head>
<body>
    <div class="login-container">
        <h2>Iniciar Sesion</h2>
        <form action="LoginServlet" method="post">
            <label for="nombreUsuario">Usuario:</label>
            <input type="text" id="nombreUsuario" name="nombreUsuario" required>
            <label for="contrasena">Contraseña:</label>
            <input type="password" id="contrasena" name="contrasena" required>
            <input type="submit" value="Iniciar Sesión">
        </form>
        <% if (request.getAttribute("mensajeError") != null) { %>
            <p class="error"><%= request.getAttribute("mensajeError") %></p>
        <% } %>
    </div>
</body>
</html>