package com.banco.datos;

import com.banco.entidades.Usuario;
import com.banco.seguridad.Seguridad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {
    private DatabaseUtil databaseUtil = new DatabaseUtil();

    public Usuario obtenerUsuarioPorCredenciales(String nombreUsuario, String contrasena) throws SQLException {
        String query = "SELECT id_usuario, nombre_usuario, contrasena, rol FROM usuarios WHERE nombre_usuario = ? AND activo = TRUE";
        try (Connection conn = databaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nombreUsuario);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String contrasenaHash = rs.getString("contrasena");
                if (Seguridad.verifyPassword(contrasena, contrasenaHash)) {
                    Usuario usuario = new Usuario();
                    usuario.setId(rs.getLong("id_usuario"));
                    usuario.setNombreUsuario(rs.getString("nombre_usuario"));
                    usuario.setContrasena(contrasenaHash);
                    usuario.setRol(rs.getString("rol"));
                    return usuario;
                }
            }
        }
        return null;
    }
}