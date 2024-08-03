package br.com.fiap.postech.hackapay.security;

import java.util.UUID;

public class User {
    private UUID id;
    private String usuario;
    private String password;

    public User(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
    }

    public UUID getId() {
        return id;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getPassword() {
        return password;
    }
}
