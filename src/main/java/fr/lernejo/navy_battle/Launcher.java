package fr.lernejo.navy_battle;

import java.io.IOException;
import java.net.URISyntaxException;

public class Launcher {
    public static void main(String[] args) throws IOException, URISyntaxException {
        Server server = null;
        try {
            server = new Server(args[0]);
            if (args.length > 1) {
                Client client = new Client(server, args[1]);
                try {
                    client.connect();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException | URISyntaxException error) {
            throw error;
        }
    }
}
