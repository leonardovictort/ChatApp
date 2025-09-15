import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {


    private static final List<PrintWriter> clientes = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(12345);
        System.out.println("Servidor de chat iniciado na porta 12345...");

        while (true) {
            Socket clienteSocket = serverSocket.accept();
            System.out.println("Cliente conectado: " + clienteSocket.getInetAddress());


            new Thread(() -> {
                try {
                    BufferedReader entrada = new BufferedReader(
                            new InputStreamReader(clienteSocket.getInputStream())
                    );
                    PrintWriter saida = new PrintWriter(clienteSocket.getOutputStream(), true);


                    synchronized (clientes) {
                        clientes.add(saida);
                    }

                    String mensagem;
                    while ((mensagem = entrada.readLine()) != null) {
                        System.out.println("Recebido: " + mensagem);


                        synchronized (clientes) {
                            for (PrintWriter c : clientes) {
                                c.println(mensagem);
                            }
                        }
                    }

                    synchronized (clientes) {
                        clientes.remove(saida);
                    }
                    clienteSocket.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
