package main;

import Listas.Lista;

public class Queue<T> {

    //    private Nodo<T> first;
    //    private Nodo<T> end;
    private int length;
    private Lista<T> lista;

    public Queue() {
        this.lista = new Lista();
    }

    public void insert(T item){
        lista.agregarFinal(item);
        length++;
    }

    public T delete(){
        T eliminado = lista.eliminarInicio();
        length--;
        return eliminado;
    }

    public int getLength() {
        return length;
    }
}
