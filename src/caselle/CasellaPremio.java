package caselle;

import partita.Player;
import partita.Dado;

public class CasellaPremio extends AbstractCasella {
    private CaselleSpeciali tipo;
    private int avanzamento = 0;

    public CasellaPremio(int posizione, CaselleSpeciali tipo) {
        super(posizione);
        this.tipo = tipo;
    }

    public CaselleSpeciali getTipo() { return tipo; }

    @Override
    public void esegui(Player p) {
        Dado dado = p.getD();
        if(tipo==CaselleSpeciali.DADI) {
            System.out.println("Il giocatore è finito sulla casella premio 'DADI' farà un altro turno, rilancia i dadi!");
            avanzamento = dado.eseguiLancio();
            int nuovaPosizione = p.getPosizione() + avanzamento;
            p.setPosizione(nuovaPosizione);
            System.out.println("Il giocatore avanzerà di " + avanzamento + " caselle!");
            System.out.println("Nuova posizione: " + p.getPosizione());
        } else if(tipo==CaselleSpeciali.MOLLA) {
            int ultimoAvanzo = p.getAvanzo();
            if(ultimoAvanzo>0) { //controllo che avanzo sia valido
                p.setPosizione(p.getPosizione() + ultimoAvanzo);
                System.out.println("Il giocatore avanza ancora del punteggio ottenuto con l’ultimo lancio di dadi!");
                System.out.println("Nuova posizione: " + p.getPosizione());
            }
        }
    }

    public int getAvanzamento() { return avanzamento; }

}
