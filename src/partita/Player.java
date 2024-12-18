package partita;

import caselle.AbstractCasella;

public class Player {
    private int id;
    private Dado d;
    private boolean unDado;
    private int posizione;
    private int turniInAttesa;
    private int avanzo = 0;
    private int mettereCartaDaParte = 0;

    public Player(int id, boolean unDado) {
        this.id = id;
        this.d = new Dado(unDado);
        this.posizione = 0;
        this.turniInAttesa = 0;
    }

    public void avviaGiocatore(Board board) {
        if(this.turniInAttesa > 0) {
            turniInAttesa--;
            System.out.println("Il giocatore " + id + " deve aspettare ancora " + turniInAttesa + " turni!");
            return; //evita di lanciare i dadi o di muoversi
        }

        spostaPlayer(board);
    }

    private void spostaPlayer(Board board) {
        int avanzamento = 0;
        if(this.posizione >= board.getLenght()-6) { //se è nelle ultime 6 celle
            avanzamento = d.eseguiLancioSingolo(); //lancia un solo dado
        } else {
            avanzamento = d.eseguiLancio(); //lancia uno o due dadi a seconda delle regole del gioco
        }
        avanzo = avanzamento; //serve per gestire la casella premio "molla"
        System.out.println("Il giocatore " + id + " deve avanzare di " + avanzamento + " caselle!");
        this.posizione += avanzamento;
        System.out.println("Il giocatore " + id + " è arrivato alla casella " + posizione);
        if(avanzamento == 12) {
            System.out.println("Il giocatore " + id + " ha fatto un doppio 6, ha diritto ad un altro turno");
            int av = d.eseguiLancio();
            System.out.println("Il giocatore " + id + " deve avanzare di " + av + " caselle!");
            this.posizione += av;
            System.out.println("Il giocatore " + id + " è arrivato alla casella " + posizione);
        }

        if(this.posizione == board.getUltimaCasella()) {
            System.out.println("Il gioco è finito! Il vincitore è il giocatore " + id);
        } else if(this.posizione > board.getUltimaCasella()) {
            int overflow = this.posizione - board.getUltimaCasella(); //il giocatore supera il margine del tabellone
            this.posizione = board.getUltimaCasella() - overflow;
            System.out.println("Il giocatore " + id + " deve indietreggiare di " + overflow + " caselle e arriva alla casella " + this.posizione);
        } else {
            AbstractCasella casellaAttuale = board.getCasella(posizione);
            if(casellaAttuale != null) {
                //System.out.println("Il giocatore " + id + " si trova sulla casella " + casellaAttuale.getPosizione());
                casellaAttuale.esegui(this);
            } else {
                System.out.println("La casella non fa parte delle caselle speciali, il gioco continua normalmente!");
            }
        }
    }

    public int getPosizione() { return posizione; }

    public void setPosizione(int posizione) {
        this.posizione = posizione;
    }

    public int getAvanzo() {
        return avanzo;
    }

    public void impostaTurniInAttesa(int turni) {
        turniInAttesa = turni;
    }

    public int getMettereCartaDaParte() {
        return mettereCartaDaParte;
    }

    public void mettereCartaDaParte() {
        mettereCartaDaParte++; //il giocatore ha pescato una carta "DIVIETODISOSTA"
    }

    public void usaCartaDaParte() {
        if(mettereCartaDaParte > 0) {
            mettereCartaDaParte--;
            System.out.println("Il giocatore " + id + " ha usato una carta 'divieto di sosta'");
        }
    }

    public int getId() {
        return id;
    }

    public Dado getD() {
        return d;
    }
}

