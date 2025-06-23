package com.banco.servlets;

import com.banco.entidades.Usuario;
import com.banco.negocio.AutenticacionService;
import com.banco.negocio.AutenticacionFallidaException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AutenticacionService autenticacionService = new AutenticacionService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombreUsuario = request.getParameter("nombreUsuario");
        String contrasena = request.getParameter("contrasena");

        try {
            Usuario usuario = autenticacionService.login(nombreUsuario, contrasena);
            HttpSession session = request.getSession();
            session.setAttribute("usuarioLogueado", usuario);

            if (usuario.esAdministrador()) {
                response.sendRedirect(request.getContextPath() + "/admin/clientes/listar.jsp");
            } else if (usuario.esCliente()) {
                response.sendRedirect(request.getContextPath() + "/cliente/cuentas/listar.jsp");
            }
        } catch (AutenticacionFallidaException e) {
            request.setAttribute("mensajeError", e.getMessage());
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("mensajeError", "Error en la base de datos: " + e.getMessage());
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}