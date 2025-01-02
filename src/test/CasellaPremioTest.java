package test;

import partita.*;
import caselle.*;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class CasellaPremioTest {

    private Player player;
    private Board board;
    private Dado dado;
    private CasellaPremio casellaPremioDadi;
    private CasellaPremio casellaPremioMolla;

    @BeforeAll
    public static void setUpBeforeAll() {
        System.out.println("-------TEST INIZIATI-------");
    }

    @BeforeEach
    void setup() {
        dado = new Dado(true);
        player = new Player(1, true);
        player.setPosizione(0);
        board = new Board(36, null);
        casellaPremioDadi = new CasellaPremio(5, CaselleSpeciali.DADI);
        casellaPremioMolla = new CasellaPremio(10, CaselleSpeciali.MOLLA);
    }

    @Test
    @DisplayName("Test Creazione CasellaPremio DADI")
    void testCreazioneCasellaPremioDadi() {
        assertNotNull(casellaPremioDadi, "La casella premio tipo 'DADI' dovrebbe essere creata.");
    }

    @Test
    @DisplayName("Test Creazione CasellaPremio MOLLA")
    void testCreazioneCasellaPremioMolla() {
        assertNotNull(casellaPremioMolla, "La casella premio tipo 'MOLLA' dovrebbe essere creata.");
    }

    @Test
    @DisplayName("Test Effetto CasellaPremio DADI")
    void testEffettoCasellaPremioDadi() {
        player.setPosizione(0);
        casellaPremioDadi.esegui(player);

        int valoreDado = casellaPremioDadi.getAvanzamento();

        int nuovaPosizione = 0 + valoreDado;
        assertEquals(nuovaPosizione, player.getPosizione(), "La posizione del giocatore non è aggiornata correttamente.");

        assertEquals(valoreDado, casellaPremioDadi.getAvanzamento(), "Il valore del dado non corrisponde al valore generato.");
    }

    @Test
    @DisplayName("Test Effetto CasellaPremio MOLLA")
    void testEffettoCasellaPremioMolla() {
        player.setPosizione(5);
        player.setAvanzo(3);
        casellaPremioMolla.esegui(player);

        assertEquals(8, player.getPosizione(), "La posizione del giocatore dovrebbe essere 8 dopo l'effetto della 'MOLLA'.");
    }

    @AfterEach
    public void dopoIlSingolo() {
        System.out.println("SINGOLO TEST EFFETTUATO");
    }

    @AfterAll
    public static void fine() {
        System.out.println("-------TEST FINITI-------");
    }
}
