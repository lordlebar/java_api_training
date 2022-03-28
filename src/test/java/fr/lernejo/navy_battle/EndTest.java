package fr.lernejo.navy_battle;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class EndTest {
    @Test
    void wrong_argument() {
        End a = new End(null);
        try {
            a.Shoot(new int[]{1, 2});
            assertEquals(Game.FireResult.out, a.Shoot(new int[]{1, 2}), "bad shot is");
        } catch (Exception e) {
        }
    }

    @Test
    void end_game() throws IOException {
        Game g = new Game( new Server("4537"));
        g.server.target[0] = "http://localhost:4537";
        End a = new End(g);
        a.Shoot(new int[]{1,1});
    }
}