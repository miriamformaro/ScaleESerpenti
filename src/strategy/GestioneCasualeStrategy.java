package strategy;

import caselle.CasellaNormale;
import caselle.CaselleSpeciali;
import partita.Board;

import java.util.Random;

public class GestioneCasualeStrategy implements GestioneBoardStrategy {
    Random random = new Random();

    @Override
    public void gestioneBoard(Board board, int numeroCaselle) {
        int numeroSerpenti = 2+random.nextInt(3);
        int numeroScale = 2+random.nextInt(3);
        int larghezza = (int) Math.ceil(Math.sqrt(numeroCaselle));

        System.out.println("Numero di serpenti da inserire: " + numeroSerpenti);
        for(int i = 0; i < numeroSerpenti; i++) {
            int testa = random.nextInt(numeroCaselle-1);
            int coda = random.nextInt(testa);
            if(!board.getCaselleUsate().contains(testa) && !board.getCaselleUsate().contains(coda) && !stessaRiga(testa, coda, larghezza)) {
                board.getCaselle()[testa] = board.getCasellaFactory().creaCasella(CaselleSpeciali.SERPENTE, testa, coda);
            }
            board.getCaselleUsate().add(testa);
            board.getCaselleUsate().add(coda);
            System.out.println("La coda del serpente è alla casella: " + coda);
            System.out.println("La testa del serpente è alla casella: " + testa);
        }
    }

    private boolean stessaRiga(int pos1, int pos2, int larghezza) {
        return (pos1/larghezza == pos2/larghezza);
    }
}
