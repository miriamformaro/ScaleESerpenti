package caselle;

import java.util.Random;

public class Casella implements CasellaFactory {

    @Override
    public AbstractCasella creaCasella(CaselleSpeciali tipo, int posizione, int fine) {
        switch (tipo) {
            case SCALA:
                return new CasellaScala(posizione, fine);
            case SERPENTE:
                return new CasellaSerpente(posizione, fine);
            case PANCHINA:
                return new CasellaSosta(posizione, CaselleSpeciali.PANCHINA);
            case LOCANDA:
                return new CasellaSosta(posizione, CaselleSpeciali.LOCANDA);
            case DADI:
                return new CasellaPremio(posizione, CaselleSpeciali.DADI);
            case MOLLA:
                return new CasellaPremio(posizione, CaselleSpeciali.MOLLA);
            case PESCA_UNA_CARTA:
                return new CasellaPescaUnaCarta(posizione, pescaUnaCarta());
            default:
                throw new  IllegalArgumentException("Tipo non valido");
        }
    }

    private CaselleSpeciali pescaUnaCarta() {
        CaselleSpeciali[] valori = {CaselleSpeciali.PANCHINA, CaselleSpeciali.LOCANDA, CaselleSpeciali.DADI, CaselleSpeciali.MOLLA, CaselleSpeciali.DIVIETO_DI_SOSTA};
        int i = new Random().nextInt(valori.length);
        return valori[i];
    }
}
