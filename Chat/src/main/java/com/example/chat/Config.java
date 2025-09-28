package com.example.chat;

import java.io.InputStream;
import java.util.Properties;

public class Config {

    private String ipServidor;
    private int porta;

    public Config() throws Exception {
        Properties props = new Properties();

        try (InputStream input = getClass().getResourceAsStream("/com/example/chat/config.txt")) {
            if (input != null) {
                props.load(input);
            } else {
                System.out.println("⚠️ Arquivo config.txt não encontrado, usando valores padrão.");
            }
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
