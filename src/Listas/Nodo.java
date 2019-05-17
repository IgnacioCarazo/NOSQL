package Listas;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class Nodo<T> {

    public T item;
    public Nodo<T> next;


    public Nodo(T item) {
        this.item = item;
    }

    public T getDato() {
        return item;
    }
    public Nodo<T> getNext() {
        return next;
    }

    public Nodo() {
    }
}
