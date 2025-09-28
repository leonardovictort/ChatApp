package com.example.chat;

public class User {

    private String nome;

    public User() {
    }

    public User(String nome) {
        this.nome = nome;
    }

    public boolean usuarioIdentificado(){
        if(this != null && !this.getNome().isEmpty()){
            return true;
        } else
            return false;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
