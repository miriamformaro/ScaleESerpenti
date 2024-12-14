package caselle;

import Gioco.DadoFactory;
import Gioco.Player;
import Gioco.Dado;

public class CasellaPremio extends AbstractCasella {
    private CaselleSpeciali tipo;

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
            int avanzamento = dado.eseguiLancio();
            p.setPosizione(p.getPosizione() + avanzamento);
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
}
