package com.banco.entidades;

public class Usuario {
    private long id;
    private String nombreUsuario;
    private String contrasena;
    private String rol;

    // Getters y Setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public String getNombreUsuario() { return nombreUsuario; }
    public void setNombreUsuario(String nombreUsuario) { this.nombreUsuario = nombreUsuario; }
    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }
    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    // Metodos para verificar roles
    public boolean esAdministrador() {
        return "ADMIN".equalsIgnoreCase(rol);
    }

    public boolean esCliente() {
        return "CLIENTE".equalsIgnoreCase(rol);
    }
}