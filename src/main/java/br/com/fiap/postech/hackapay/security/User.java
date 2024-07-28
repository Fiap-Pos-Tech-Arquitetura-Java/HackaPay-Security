package br.com.fiap.postech.hackapay.security;

import java.util.Objects;
import java.util.UUID;

public class User {
    private UUID id;
    private String login;
    private String password;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public UUID getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
