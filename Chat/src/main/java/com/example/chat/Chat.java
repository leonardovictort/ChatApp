package com.example.chat;

import javafx.application.Platform;
import javafx.scene.control.ListView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Chat {

    private Socket socket;
    private PrintWriter saida;
    private BufferedReader entrada;
    private Config config;

    public Chat() throws Exception {
        this.config = new Config();

        socket = new Socket(config.getIpServidor(), config.getPorta());
        saida = new PrintWriter(socket.getOutputStream(), true);
        entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void enviarMensagem(Mensagem mensagem) {
        saida.println(mensagem.toString());
    }

    public void receberMensagens(ListView<String> mensagensList) {
        new Thread(() -> {
            try {
                String linha;
                while ((linha = entrada.readLine()) != null) {
                    String finalLinha = linha;
                    Platform.runLater(() -> mensagensList.getItems().add(finalLinha));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void fechar() throws Exception {
        if (entrada != null) entrada.close();
        if (saida != null) saida.close();
        if (socket != null && !socket.isClosed()) socket.close();
    }
}
