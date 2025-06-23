package com.banco.negocio;

import com.banco.datos.UsuarioDAO;
import com.banco.entidades.Usuario;
import java.sql.SQLException;

public class AutenticacionService {
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    public Usuario login(String nombreUsuario, String contrasena) throws SQLException, AutenticacionFallidaException {
        Usuario usuario = usuarioDAO.obtenerUsuarioPorCredenciales(nombreUsuario, contrasena);
        if (usuario == null) {
            throw new AutenticacionFallidaException("Usuario o contraseña incorrectos.");
        }
        return usuario;
    }
}