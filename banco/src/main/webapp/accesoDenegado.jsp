<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Acceso Denegado - Sistema Bancario</title>
    <style>
        body { font-family: Arial, sans-serif; display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; }
        .error-container { text-align: center; }
        .error-container h2 { color: red; }
        a { color: #007bff; text-decoration: none; }
        a:hover { text-decoration: underline; }
    </style>
</head>
<body>
    <div class="error-container">
        <h2>Acceso Denegado</h2>
        <p>No tienes permisos para acceder a esta página.</p>
        <a href="<%= request.getContextPath() %>/login.jsp">Volver al Inicio de Sesión</a>
        <br>
        <a href="<%= request.getContextPath() %>/LogoutServlet">Cerrar Sesión</a>
    </div>
</body>
</html>