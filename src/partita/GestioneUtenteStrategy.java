package partita;

import caselle.AbstractCasella;
import caselle.CaselleSpeciali;

import java.util.Scanner;

public class GestioneUtenteStrategy implements GestioneBoardStrategy{
    @Override
    public void gestioneBoard(Board board, int numeroCaselle) {
        int numeroSerpenti = ottieniNumero("Quanti serpenti vuoi inserire? ");
        for(int i=0; i<numeroSerpenti; i++) {
            int testa = ottieniPosizione("Inserisci la posizione della testa del serpente: ");
            int coda = ottieniPosizione("Inserisci la posizione della coda del serpente: ");
            if(testa > coda && !board.getCaselleUsate().contains(testa) && !board.getCaselleUsate().contains(coda)) {
                board.aggiungiCasella(CaselleSpeciali.SERPENTE, testa, coda);
            } else {
                System.out.println("Impossibile aggiungere il serpente!");
            }
        }
    }

    private int ottieniNumero(String domanda) {
        Scanner sc = new Scanner(System.in);
        System.out.print(domanda);
        int numero = sc.nextInt();
        return numero;
    }

    private int ottieniPosizione(String domanda) {
        Scanner sc = new Scanner(System.in);
        System.out.println(domanda);
        int posizione = sc.nextInt();
        return posizione;
    }
}
