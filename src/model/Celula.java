package model;

public class Celula<T> {
    T item;
    Celula<T> prox;

    public Celula(T item) {
        this.item = item;
    }
}