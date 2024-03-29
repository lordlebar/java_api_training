package fr.lernejo.navy_battle;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ServerTest {

    static Server StartTestServer(String port) throws Exception {
        try {
            return new Server(port);
        } catch (IOException e) {
            Assertions.assertEquals(0, 1, "Number of exception");
            throw new Exception("Test server failed to launch");
        }
    }

    static void StopServer(Server server) {
        if (server == null)
            return;
        server.server.stop(0);
    }

    @Test
    void ping() {
        Server server = null;
        try {
            server = StartTestServer("9696");
        } catch (Exception e) {
            Assertions.assertEquals(0, 1, "Number of exception on server");
        }
        try {
            HttpClient cli = HttpClient.newHttpClient();
            HttpRequest requetePost = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:9696/ping"))
                .setHeader("Accept", "application/json")
                .setHeader("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString("Ping"))
                .build();
            HttpResponse<String> response = cli.send(requetePost, HttpResponse.BodyHandlers.ofString());
            Assertions.assertEquals(200, response.statusCode(), "Status code 200 was expected for a POST on /ping");
            Assertions.assertEquals("OK", response.body(), "OK body was expected for a POST  on /ping");
        } catch (URISyntaxException | InterruptedException | IOException e) {
            Assertions.assertEquals(0, 1, "Number of exception for POST on /ping");
        }
        StopServer(server);
    }

    @Test
    void drop() {
        Server server = null;
        try {
            server = StartTestServer("9212");
        } catch (Exception e) {
            Assertions.assertEquals(0, 1, "Number of exception");
        }
        try {
            HttpClient cli = HttpClient.newHttpClient();
            HttpRequest requetePost = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:9212/wtf"))
                .setHeader("Accept", "application/json")
                .setHeader("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString("Ping"))
                .build();
            HttpResponse<String> response = cli.send(requetePost, HttpResponse.BodyHandlers.ofString());
            Assertions.assertEquals(404, response.statusCode(), "404 was expected for a POST on /wtf");
        } catch (URISyntaxException | InterruptedException | IOException e) {
            Assertions.assertEquals(0, 1, "Number of exception");
        }

        try {
            HttpClient cli = HttpClient.newHttpClient();
            HttpRequest requetePost = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:9212/api/game/fire"))
                .setHeader("Accept", "application/json")
                .setHeader("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString("Ping"))
                .build();
            HttpResponse<String> response = cli.send(requetePost, HttpResponse.BodyHandlers.ofString());
            Assertions.assertEquals(404, response.statusCode(), "Status code 404 was expected for a POST on /yolo");
        } catch (URISyntaxException | InterruptedException | IOException e) {
            Assertions.assertEquals(0, 1, "Number of exception");
        }

        try {
            HttpClient cli = HttpClient.newHttpClient();
            HttpRequest requeteget = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:9212/api/game/fire"))
                .setHeader("Accept", "application/json")
                .setHeader("Content-Type", "application/json")
                .GET()
                .build();
            HttpResponse<String> response = cli.send(requeteget, HttpResponse.BodyHandlers.ofString());
            Assertions.assertEquals(400, response.statusCode(), "Status code 404 was expected for a POST on /yolo");
        } catch (URISyntaxException | InterruptedException | IOException e) {
            Assertions.assertEquals(0, 1, "Number of exception");
        }

        try {
            HttpClient cli = HttpClient.newHttpClient();
            HttpRequest requetePost = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:9212/api/game/start"))
                .setHeader("Accept", "application/json")
                .setHeader("Content-Type", "application/json")
                .GET()
                .build();
            HttpResponse<String> response = cli.send(requetePost, HttpResponse.BodyHandlers.ofString());
            Assertions.assertEquals(404, response.statusCode(), "Status code 404 was expected for get on /api/game/start");
        } catch (URISyntaxException | InterruptedException | IOException e) {
            Assertions.assertEquals(0, 1, "Number of exception");
        }
        server.game.ingame[0] = true;
        try {
            HttpClient cli = HttpClient.newHttpClient();
            HttpRequest requetePost = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:9212/api/game/start"))
                .setHeader("Accept", "application/json")
                .setHeader("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString("Ping"))
                .build();
            HttpResponse<String> response = cli.send(requetePost, HttpResponse.BodyHandlers.ofString());
            Assertions.assertEquals(404, response.statusCode(), "Status code 404 was expected for get on /api/game/start");
        } catch (URISyntaxException | InterruptedException | IOException e) {
            Assertions.assertEquals(0, 1, "Number of exception");
        }
        StopServer(server);
    }
}
