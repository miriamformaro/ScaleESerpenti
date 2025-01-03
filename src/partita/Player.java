package partita;

import observer.Observer;
import observer.Subject;
import caselle.AbstractCasella;

import java.util.HashSet;
import java.util.Set;

public class Player implements Subject {
    private int id;
    private Dado d;
    private int posizione;
    private int turniInAttesa;
    private int avanzo = 0;
    private int mettereCartaDaParte = 0;
    private Set<Observer> observers;
    private int avanzoDado = 0;
    private int posizioneIniziale;

    public Player(int id, boolean unDado) {
        this.id = id;
        this.d = new Dado(unDado);
        this.posizione = -1;
        this.turniInAttesa = 0;
        this.observers = new HashSet<>();
    }

    public void registraOsservatore(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }

    @Override
    public void rimuoviOsservatore(Observer observer) {
        this.observers.remove(observer);
    }

    public void avviaGiocatore(Board board) {
        if(this.turniInAttesa > 0) {
            turniInAttesa--;
            //System.out.println("Il giocatore " + id + " deve aspettare ancora " + turniInAttesa + " turni!");
            return;
        }

        spostaPlayer(board);
    }


    private void spostaPlayer(Board board) {
        this.setPosizioneIniziale(this.posizione);
        int avanzamento = 0;
        avanzo = 0;

        // Se il giocatore è fuori dal tabellone (posizione == -1), al primo lancio avanza come dal dado
        if (this.posizione == -1) {
            avanzamento = d.eseguiLancio();  // Lancia il dado normalmente
            this.posizione += avanzamento;    // La posizione diventa il numero lanciato
            //System.out.println("Il giocatore " + id + " si è posizionato sulla casella " + posizione);
        } else {
            // Se il giocatore è già sul tabellone, usa il dado per avanzare normalmente
            if(this.posizione >= board.getLenght()-6) {
                avanzamento = d.eseguiLancioSingolo();
            } else {
                avanzamento = d.eseguiLancio();
            }
            avanzo = avanzamento;
            //System.out.println("Il giocatore " + id + " deve avanzare di " + avanzamento + " caselle!");
            this.posizione += avanzamento;
            //System.out.println("Il giocatore " + id + " è arrivato alla casella " + posizione);
        }

        notifyObservers();

        if(avanzamento == 12) {
            //System.out.println("Il giocatore " + id + " ha fatto un doppio 6, ha diritto ad un altro turno");
            avanzoDado = d.eseguiLancio();
            //System.out.println("Il giocatore " + id + " deve avanzare di " + avanzoDado + " caselle!");
            this.posizione += avanzoDado;
            //System.out.println("Il giocatore " + id + " è arrivato alla casella " + posizione);
        }

        if(this.posizione == board.getUltimaCasella()) {
            //System.out.println("Il gioco è finito! Il vincitore è il giocatore " + id);
        } else if(this.posizione > board.getUltimaCasella()) {
            int overflow = this.posizione - board.getUltimaCasella(); //il giocatore supera il margine del tabellone
            this.posizione = board.getUltimaCasella() - overflow;
            //System.out.println("Il giocatore " + id + " deve indietreggiare di " + overflow + " caselle e arriva alla casella " + this.posizione);
        } else {
            AbstractCasella casellaAttuale = board.getCasella(posizione);
            if(casellaAttuale != null) {
                //System.out.println("Il giocatore " + id + " si trova sulla casella " + casellaAttuale.getPosizione());
                casellaAttuale.esegui(this);
                if(this.posizione > board.getUltimaCasella()) {
                    int overflow = this.posizione - board.getUltimaCasella(); //il giocatore supera il margine del tabellone
                    this.posizione = board.getUltimaCasella() - overflow;
                    //System.out.println("Il giocatore " + id + " deve indietreggiare di " + overflow + " caselle e arriva alla casella " + this.posizione);
                }
            } else {
                //System.out.println("La casella non fa parte delle caselle speciali, il gioco continua normalmente!");
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

    public void setMettereCartaDaParte(int mettereCartaDaParte) {
        this.mettereCartaDaParte = mettereCartaDaParte;
    }

    public void mettereCartaDaParte() {
        mettereCartaDaParte++; //il giocatore ha pescato una carta "DIVIETODISOSTA"
    }

    public void usaCartaDaParte() {
        if(mettereCartaDaParte > 0) {
            mettereCartaDaParte--;
            //System.out.println("Il giocatore " + id + " ha usato una carta 'divieto di sosta'");
        }
    }

    public int getId() {
        return id;
    }

    public Dado getD() {
        return d;
    }

    public int getAvanzoDado() {
        return avanzoDado;
    }

    public int getPosizioneIniziale() {
        return posizioneIniziale;
    }

    public void setPosizioneIniziale(int posizioneIniziale) {
        this.posizioneIniziale = posizioneIniziale;
    }

    public void setAvanzo(int i) {
        avanzo = i;
    }

    public int getTurniInAttesa() {
        return turniInAttesa;
    }
}

