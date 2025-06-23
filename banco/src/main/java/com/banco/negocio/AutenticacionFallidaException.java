package com.banco.negocio;

public class AutenticacionFallidaException extends Exception {
    private static final long serialVersionUID = 1L;

    public AutenticacionFallidaException(String message) {
        super(message);
    }
}