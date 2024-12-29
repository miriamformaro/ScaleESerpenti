package test;

import partita.*;
import caselle.*;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class CasellaSostaTest {

    private Player player;
    private Board board;
    private CasellaSosta casellaSostaPanchina;
    private CasellaSosta casellaSostaLocanda;

    @BeforeAll
    public static void setUpBeforeAll() {
        System.out.println("-------TEST INIZIATI-------");
    }

    @BeforeEach
    void setup() {
        player = new Player(1, true);
        player.setPosizione(0);
        board = new Board(36, null);

        casellaSostaPanchina = new CasellaSosta(5, CaselleSpeciali.PANCHINA);
        casellaSostaLocanda = new CasellaSosta(10, CaselleSpeciali.LOCANDA);
    }

    @Test
    @DisplayName("Test Creazione CasellaSosta PANCHINA")
    void testCreazioneCasellaSostaPanchina() {
        assertNotNull(casellaSostaPanchina, "La casella sosta tipo 'PANCHINA' dovrebbe essere creata.");
    }

    @Test
    @DisplayName("Test Creazione CasellaSosta LOCANDA")
    void testCreazioneCasellaSostaLocanda() {
        assertNotNull(casellaSostaLocanda, "La casella sosta tipo 'LOCANDA' dovrebbe essere creata.");
    }

    @Test
    @DisplayName("Test Effetto CasellaSosta PANCHINA senza carta")
    void testEffettoCasellaSostaPanchinaSenzaCarta() {
        player.setPosizione(0);
        player.setMettereCartaDaParte(0);
        casellaSostaPanchina.esegui(player);

        assertEquals(1, player.getTurniInAttesa(), "Il giocatore dovrebbe essere in attesa per 1 turno sulla panchina.");
    }

    @Test
    @DisplayName("Test Effetto CasellaSosta PANCHINA con carta")
    void testEffettoCasellaSostaPanchinaConCarta() {
        player.setPosizione(0);
        player.setMettereCartaDaParte(1);
        casellaSostaPanchina.esegui(player);

        assertEquals(0, player.getTurniInAttesa(), "Il giocatore dovrebbe continuare a giocare grazie alla carta di divieto.");
    }

    @Test
    @DisplayName("Test Effetto CasellaSosta LOCANDA con 0 carte")
    void testEffettoCasellaSostaLocandaZeroCarte() {
        player.setPosizione(5);
        player.setMettereCartaDaParte(0);
        casellaSostaLocanda.esegui(player);

        assertEquals(3, player.getTurniInAttesa(), "Il giocatore dovrebbe essere in attesa per 3 turni nella locanda.");
    }

    @Test
    @DisplayName("Test Effetto CasellaSosta LOCANDA con 1 carta")
    void testEffettoCasellaSostaLocandaCon1Carta() {
        player.setPosizione(5);
        player.setMettereCartaDaParte(1);
        casellaSostaLocanda.esegui(player);

        assertEquals(2, player.getTurniInAttesa(), "Il giocatore dovrebbe essere in attesa per 2 turni nella locanda.");
    }

    @Test
    @DisplayName("Test Effetto CasellaSosta LOCANDA con 2 carte")
    void testEffettoCasellaSostaLocandaCon2Carte() {
        player.setPosizione(5);
        player.setMettereCartaDaParte(2);
        casellaSostaLocanda.esegui(player);

        assertEquals(1, player.getTurniInAttesa(), "Il giocatore dovrebbe essere in attesa per 1 turno nella locanda.");
    }

    @Test
    @DisplayName("Test Effetto CasellaSosta LOCANDA con 3 carte")
    void testEffettoCasellaSostaLocandaCon3Carte() {
        player.setPosizione(5);
        player.setMettereCartaDaParte(3);
        casellaSostaLocanda.esegui(player);

        assertEquals(0, player.getTurniInAttesa(), "Il giocatore non dovrebbe essere in attesa nella locanda grazie alle carte.");
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
