package com.banco.filtros;

import com.banco.entidades.Usuario;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class FiltroAcceso implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws javax.servlet.ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, javax.servlet.ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);
        String requestURI = httpRequest.getRequestURI();

        // Permitir acceso a login
        if (requestURI.endsWith("login.jsp") || requestURI.endsWith("LoginServlet")) {
            chain.doFilter(request, response);
            return;
        }

        // Verificar usuario logueado
        Usuario usuarioLogueado = (session != null) ? (Usuario) session.getAttribute("usuarioLogueado") : null;

        if (usuarioLogueado == null) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsp");
            return;
        }

        // Verificar acceso segun rol
        if (requestURI.startsWith(httpRequest.getContextPath() + "/admin/") && !usuarioLogueado.esAdministrador()) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/accesoDenegado.jsp");
            return;
        }
        if (requestURI.startsWith(httpRequest.getContextPath() + "/cliente/") && !usuarioLogueado.esCliente()) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/accesoDenegado.jsp");
            return;
        }

        // Mostrar nombre de usuario en la sesion
        request.setAttribute("nombreUsuario", usuarioLogueado.getNombreUsuario());
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {}
}