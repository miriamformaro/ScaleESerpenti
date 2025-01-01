package caselle;

import partita.Player;

import java.util.Random;

public class CasellaPescaUnaCarta extends AbstractCasella{
    private CaselleSpeciali tipo;
    private CasellaSosta casellaSosta;
    private CasellaPremio casellaPremio;

    public CasellaPescaUnaCarta(int posizione, CaselleSpeciali tipo) {
        super(posizione);
        this.tipo = tipo;
    }

    @Override
    public void esegui(Player p) {
        tipo = pescaUnaCarta();
        //System.out.println("Il giocatore si trova su una casella 'Pesca una carta', ha pescato la carta: " + tipo);
        if(tipo==CaselleSpeciali.PANCHINA || tipo==CaselleSpeciali.LOCANDA) {
            p.notifyObservers();
            casellaSosta = new CasellaSosta(this.getPosizione(), tipo);
            casellaSosta.esegui(p);
        } else if(tipo==CaselleSpeciali.DADI || tipo==CaselleSpeciali.MOLLA) {
            p.notifyObservers();
            casellaPremio = new CasellaPremio(this.getPosizione(), tipo);
            casellaPremio.esegui(p);
        } else if(tipo==CaselleSpeciali.DIVIETO_DI_SOSTA) {
            p.notifyObservers();
            p.mettereCartaDaParte();
        }
        tipo = CaselleSpeciali.PESCA_UNA_CARTA;
    }

    @Override
    public CaselleSpeciali getTipo() {
        return tipo;
    }

    private CaselleSpeciali pescaUnaCarta() {
        CaselleSpeciali[] valori = {CaselleSpeciali.PANCHINA, CaselleSpeciali.LOCANDA, CaselleSpeciali.DADI, CaselleSpeciali.MOLLA, CaselleSpeciali.DIVIETO_DI_SOSTA};
        int i = new Random().nextInt(valori.length);
        return valori[i];
    }

    @Override
    public String toString() {
        return "Casella pescata: " + tipo;
    }
}
