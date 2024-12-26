package Observer;

public interface Subject {
    void registraOsservatore(Observer observer);
    void rimuoviOsservatore(Observer observer);
    void notifyObservers();
}
