package partita;

import caselle.CasellaScala;
import caselle.CasellaSerpente;
import strategy.GestioneBoardStrategy;
import strategy.GestioneCasualeStrategy;
import strategy.GestioneUtenteStrategy;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Gioco {
    private Board board;
    private List<Player> players;

    public Gioco(Board board, List<Player> players) {
        this.board = board;
        this.players = new LinkedList<>(players);
    }

    public void avviaGioco() {
        try {
            while(true) {
                for(Player player : players) {
                    player.avviaGiocatore(board);
                    TimeUnit.SECONDS.sleep(3);
                    if(player.getPosizione()== board.getUltimaCasella()) {
                        return;
                    }
                }
            }
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

    public List<Player> getPlayers() {
        return players;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Vuoi usare la strategia casuale o utente? ");
        GestioneBoardStrategy strategy = null;
        Board board = null;

        if(sc.next().equals("casuale")) {
            strategy = new GestioneCasualeStrategy();
        } else {
            List<CasellaSerpente> listaSerpente = new LinkedList<>();
            System.out.print("Quanti serpenti vuoi inserire? ");
            int numeroSerpenti = sc.nextInt();
            for(int i=0; i < numeroSerpenti; i++) {
                System.out.print("Inserisci la testa del serpente: ");
                int testa = sc.nextInt();
                System.out.print("Inserisci la coda del serpente: ");
                int coda = sc.nextInt();
                listaSerpente.add(new CasellaSerpente(testa, coda));
            }

            List<CasellaScala> listaScala = new LinkedList<>();
            System.out.print("Quante scale vuoi inserire? ");
            int numeroScale = sc.nextInt();
            for(int i=0; i < numeroSerpenti; i++) {
                System.out.print("Inserisci la fine di una scala: ");
                int fine = sc.nextInt();
                System.out.print("Inserisci la cima di una scala: ");
                int cima = sc.nextInt();
                listaScala.add(new CasellaScala(fine, cima));
            }
            System.out.print("Inserisci la posizione della casella panchina: ");
            int panchina = sc.nextInt();
            System.out.print("Inserisci la posizione della casella locanda: ");
            int locanda = sc.nextInt();
            System.out.print("Inserisci la posizione della casella dadi: ");
            int dadi = sc.nextInt();
            System.out.print("Inserisci la posizione della casella molla: ");
            int molla = sc.nextInt();
            System.out.println("Inserisci la posizione della casella pesca una carta: ");
            int pescaUnaCarta = sc.nextInt();
            strategy = new GestioneUtenteStrategy(listaSerpente, listaScala, panchina, locanda, dadi, molla, pescaUnaCarta);
        }

        board = new Board(100, strategy);
        board.applicaStrategia();

        System.out.print("Scegli un numero di giocatori: ");
        int numero = sc.nextInt();
        System.out.println("Numero giocatori: " + numero);

        boolean dado = false;
        System.out.print("Vuoi giocare con 1 o 2 dadi? ");
        int dadi = sc.nextInt();
        if(dadi == 1) {
            dado = true;
        } else {
            dado = false;
        }

        List<Player> players = new LinkedList<>();
        for(int i=0; i<numero; i++) {
            players.add(new Player(i, dado));
        }

        Gioco gioco = new Gioco(board, players);
        gioco.avviaGioco();
    }
}
