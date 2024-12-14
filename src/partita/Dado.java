package partita;

import java.util.Random;

public class Dado {
    private boolean dado;
    private Random rand = new Random();

    public Dado(boolean dado) {
        this.dado = dado;
    }

    public int eseguiLancio() {
        int dado1 = rand.nextInt(6) + 1;
        if(dado) {
            System.out.println("Dado: " + dado1);
            return dado1;
        }
        int dado2 = rand.nextInt(6) + 1;
        System.out.println("Dado1: " + dado1 + ", Dado2: " + dado2);
        return dado1 + dado2;
    }

    public int eseguiLancioSingolo() {
        int dado = rand.nextInt(6) + 1;
        System.out.println("Dado: " + dado);
        return dado;
    }
}
