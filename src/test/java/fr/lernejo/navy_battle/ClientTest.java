package fr.lernejo.navy_battle;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

class ClientTest {

    @Test
    void accept() throws IOException, URISyntaxException, InterruptedException {
        Client client = new Client(new Server("6789"), "http://localhost:6789");
        client.connect();
    }
}
