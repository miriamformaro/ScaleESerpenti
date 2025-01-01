package caselle;

import partita.Player;

public class CasellaSosta extends AbstractCasella {
    private CaselleSpeciali tipo;
    private int turniInAttesa = 0;

    public CasellaSosta(int posizione, CaselleSpeciali tipo) {
        super(posizione);
        this.tipo = tipo;
    }

    @Override
    public void esegui(Player p) {
        if(tipo==CaselleSpeciali.PANCHINA) {
            if(p.getMettereCartaDaParte() > 0) {
                turniInAttesa = 0;
                p.usaCartaDaParte();
                //System.out.println("Il giocatore è finito su una panchina ma ha una carta di divieto di sosta, può continuare a giocare!");
            } else {
                turniInAttesa = 1;
                //System.out.println("Il giocatore è finito su una panchina, sosta un turno!");
            }
        } else if(tipo==CaselleSpeciali.LOCANDA) {
            int carteDaParte = p.getMettereCartaDaParte();
            if(carteDaParte==0) {
                turniInAttesa = 3;
                //System.out.println("Il giocatore è finito su una locanda, sosta 3 turni!");
            } else if(carteDaParte==1) {
                turniInAttesa = 2;
                //System.out.println("Il giocatore è finito su una locanda, sosta 2 turni!");
            } else if(carteDaParte==2) {
                turniInAttesa = 1;
                //System.out.println("Il giocatore è finito su una locanda, sosta un turno!");
            } else if(carteDaParte==3) {
                turniInAttesa = 0;
                //System.out.println("Il giocatore è finito su una locanda ma ha tre carte di divieto di sosta, continua a giocare!");
            }

            if(carteDaParte>0)
                p.usaCartaDaParte();
        }
        p.impostaTurniInAttesa(turniInAttesa);
    }

    public CaselleSpeciali getTipo() {
        return tipo;
    }
}
