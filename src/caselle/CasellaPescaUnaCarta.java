package caselle;

import partita.Player;

public class CasellaPescaUnaCarta extends AbstractCasella{
    private CaselleSpeciali tipo;
    private CasellaSosta casellaSosta;
    private CasellaPremio casellaPremio;

    public CasellaPescaUnaCarta(int posizione, CaselleSpeciali tipo) {
        super(posizione);
        this.tipo = tipo;
        this.casellaSosta = new CasellaSosta(posizione, tipo);
        this.casellaPremio = new CasellaPremio(posizione, tipo);
    }

    public CaselleSpeciali getTipo() {
        return tipo;
    }

    @Override
    public void esegui(Player p) {
        if(tipo==CaselleSpeciali.PANCHINA || tipo==CaselleSpeciali.LOCANDA) {
            casellaSosta.esegui(p);
        } else if(tipo==CaselleSpeciali.DADI || tipo==CaselleSpeciali.MOLLA) {
            casellaPremio.esegui(p);
        } else if(tipo==CaselleSpeciali.DIVIETO_DI_SOSTA) {
            p.mettereCartaDaParte();
        }
    }

    @Override
    public String toString() {
        return "Casella pescata: " + tipo;
    }
}
