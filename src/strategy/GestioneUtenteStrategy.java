package strategy;

import caselle.CasellaScala;
import caselle.CasellaSerpente;
import caselle.CaselleSpeciali;
import partita.Board;

import java.util.LinkedList;
import java.util.List;

public class GestioneUtenteStrategy implements GestioneBoardStrategy {
    private List<CasellaSerpente> listaSerpenti;
    private List<CasellaScala> listaScale;
    private int panchina;
    private int locanda;
    private int dadi;
    private int molla;
    private int pescaUnaCarta;

    public GestioneUtenteStrategy(List<CasellaSerpente> listaSerpenti, List<CasellaScala> listaScale, int panchina, int locanda, int dadi, int molla, int pescaUnaCarta) {
        this.listaSerpenti = new LinkedList<>(listaSerpenti);
        this.listaScale = new LinkedList<>(listaScale);
        this.panchina = panchina;
        this.locanda = locanda;
        this.dadi = dadi;
        this.molla = molla;
        this.pescaUnaCarta = pescaUnaCarta;
    }

    @Override
    public void gestioneBoard(Board board, int numeroCaselle) {
        int larghezza = (int) Math.ceil(Math.sqrt(numeroCaselle));
        //int numeroSerpenti = ottieniNumero("Quanti serpenti vuoi inserire? ");
        for(int i=0; i<listaSerpenti.size(); i++) {
            int testa;
            int coda;
            do {
                testa = listaSerpenti.get(i).getTesta();
                coda = listaSerpenti.get(i).getCoda();

                if(testa <= coda || board.getCaselleUsate().contains(testa) || board.getCaselleUsate().contains(coda)) {
                    System.out.println("Serpente invalido! Inserirlo in altre posizioni!");
                }
            } while(testa <= coda || board.getCaselleUsate().contains(testa) || board.getCaselleUsate().contains(coda));
            board.aggiungiCasella(CaselleSpeciali.SERPENTE, testa, coda);
            board.getCaselleUsate().add(testa);
            board.getCaselleUsate().add(coda);
        }

        for(CasellaSerpente i : listaSerpenti) {
            board.aggiungiSerpente(i);
        }

        //int numeroScale = ottieniNumero("Quanti scale vuoi inserire? ");
        for(int i=0; i<listaScale.size(); i++) {
            int inizio;
            int fine;
            do {
                inizio = listaScale.get(i).getInizio();
                fine = listaScale.get(i).getFine();

                if(fine <= inizio || board.getCaselleUsate().contains(inizio) || board.getCaselleUsate().contains(fine)) {
                    System.out.println("Scala invalida! Inserirla in altre posizioni!");
                }
            } while(fine <= inizio || board.getCaselleUsate().contains(inizio) || board.getCaselleUsate().contains(fine));
            board.aggiungiCasella(CaselleSpeciali.SCALA, inizio, fine);
            board.getCaselleUsate().add(inizio);
            board.getCaselleUsate().add(fine);
        }

        for(CasellaScala i : listaScale) {
            board.aggiungiScala(i);
        }

        aggiungi(CaselleSpeciali.PANCHINA, board, panchina);
        aggiungi(CaselleSpeciali.LOCANDA, board, locanda);
        aggiungi(CaselleSpeciali.DADI, board, dadi);
        aggiungi(CaselleSpeciali.MOLLA, board, molla);
        aggiungi(CaselleSpeciali.PESCA_UNA_CARTA, board, pescaUnaCarta);
    }

    private void aggiungi(CaselleSpeciali tipo, Board board, int posizione) {
        int pos;
        do {
            pos = posizione;
        } while (board.getCaselleUsate().contains(pos));
        board.aggiungiCasella(tipo, pos, 0);
        board.getCaselleUsate().add(pos);
    }
}
