package model;

public class Fila<T> {
    private Celula<T> inicio;
    private Celula<T> fim;
    private int size;

    public Fila() {
        inicio = new Celula<T>(null);
        fim = inicio;
    }

    //adiciona item no final da fila
    public void enfileirar(T item){
        if(item != null){
            Celula<T> celula = new Celula<T>(item);
            fim.prox = celula;
            fim = fim.prox;
            size++;
        }
    }

    //retira o item no início da fila
    public T desenfileirar(){
        if(size == 0){
            throw new RuntimeException("Fila vazia");
        } else {
            inicio = inicio.prox;
            size--;
            return inicio.item;
        }
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[ ");
        Celula atual = inicio.prox;
        while (atual != fim) {
            sb.append(atual.item);
            sb.append(" ");
            atual = atual.prox;
        }
        sb.append("]");
        return sb.toString();
    }
}