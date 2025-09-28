package com.example.chat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Mensagem {

    private String mensagem;
    private LocalDateTime dataHora;
    private User remetente;

    public Mensagem(User user, String mensagem) {
        this.remetente = user;
        this.mensagem = mensagem;
        this.dataHora = LocalDateTime.now();
    }

    public String getMensagem() {
        return mensagem;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return remetente.getNome() + " [" + dataHora.format(formatter) + "]: " + mensagem;
    }
}

