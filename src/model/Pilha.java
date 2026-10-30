package model;

public class Pilha<T> {
    private Celula<T> topo;
    private int size;

    public Pilha() {
        topo = new Celula<T>(null);
    }

    //adiciona item no final da pilha
    public void empilhar(T item){
        if(item != null){
            Celula<T> celula = new Celula<T>(item);
            celula.prox = topo;
            topo = celula;
            size++;
        }
    }

    //retira o item no final da pilha
    public T desempilhar(){
        if(size == 0){
            throw new RuntimeException("Pilha vazia");
        } else {
            T item = topo.item;
            topo = topo.prox;
            size--;
            return item;
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
        Celula atual = topo.prox;
        while (atual.prox != null) {
            sb.append(atual.item);
            sb.append(" ");
            atual = atual.prox;
        }
        sb.append("]");
        return sb.toString();
    }
}
