package com.example.chat;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Properties;

public class Config {

    private String ipServidor;
    private int porta;

    public Config(String caminhoArquivo) throws Exception {
        Properties props = new Properties();
        try (BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo))) {
            props.load(reader);
        }
        this.ipServidor = props.getProperty("ipServidor", "localhost");
        this.porta = Integer.parseInt(props.getProperty("porta", "12345"));
    }

    public String getIpServidor() {
        return ipServidor;
    }

    public int getPorta() {
        return porta;
    }
}
