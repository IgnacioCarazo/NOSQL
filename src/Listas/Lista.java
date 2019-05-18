package Listas;

import main.Esquema;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.HashMap;
public class Lista<T> {

    private Nodo<T> first;
    private Nodo<T> end;
    private int length;

    public void setFirst(Nodo<T> first) {
        this.first = first;
    }

    public Nodo<T> getEnd() {
        return end;
    }

    public void setEnd(Nodo<T> end) {
        this.end = end;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Nodo<T> getFirst() {
        return first;
    }


    public void agregarInicio(T item){
        Nodo temp = first;
        first = new Nodo(item);
        first.next = temp;
        this.length++;
    }

    public boolean esVacio (){
        if (first==null){
            return true;
        }
        return false;
    }


    public void agregarFinal(T item){
        if(first==null){
            first = new Nodo(item);
        }else{
            //end.next = new Nodo(item);
            Nodo tmp = first;
            while(tmp.next!=null){
                tmp = tmp.next;
            }
            tmp.next = new Nodo(item);
        }
        length++;
    }

    public T obtenerPrimero(){
        Nodo<T> tmp = first;
        return tmp.getDato();
    }

    public T eliminarInicio(){
        Nodo tmp = first;
        first = first.next;
        return (T) tmp.item;

    }

    public int tamaño(){
        int res = 0;
        Nodo<T> tmp = first;
        while (tmp != null){
            res+=1;
            tmp = tmp.getNext();
        }
        return res;
    }


    public void imprimirListaEnlazada(){
        Nodo<T> tmp = first;
        while (tmp != null){
            System.out.println(tmp.getDato());
            tmp = tmp.getNext();
        }
    }
    //Obtiene un esquema de la listaEsquemas a partir de su nombre de la lista de Esquemas
    public Esquema obtenerEsquema(String esquemaPorObtener) {
        Nodo tmp = first;
        Esquema esquema = null;
        while (tmp != null) {
            esquema = (Esquema) tmp.getDato();
            if (esquemaPorObtener.equals(esquema.nombreEsquema)) {
                return esquema;
            } else {
                tmp = tmp.getNext();
            }
        }
        return null;
    }

    public Lista duplicarLista(Lista<T> listaADuplicar){
        Nodo<T> tempNodo = listaADuplicar.getFirst();
        Lista<T> nuevaLista = new Lista<T>();
        while(tempNodo != null) {
            nuevaLista.agregarFinal(tempNodo.item);
            tempNodo = tempNodo.next;
        }
        return nuevaLista;
    }
    // Elimina un esquema a partir de su nombre de la lista de Esquemas
    public void eliminarEsquema(String esquemaPorBorrar){
        Nodo tmp = first;
        Esquema esquema = null;
        esquema = (Esquema) tmp.next.getDato();
        while (!esquema.nombreEsquema.equals(esquemaPorBorrar)) {
            tmp = tmp.next;
            esquema = (Esquema) tmp.next.getDato();
        }
        tmp.next = tmp.next.next;
    }
    public void eliminarEsquemaInicial(){
        Nodo tmp = first;
        first = first.next;
    }

    public void eliminarInstancia(String columna, Integer ID){
        Nodo tmp = first;
        HashMap hashmap = null;
        hashmap = (HashMap) tmp.next.getDato();
        while (!hashmap.get(columna).equals(ID)){
            tmp = tmp.next;
            hashmap = (HashMap) tmp.next.getDato();
        }
        tmp.next = tmp.next.next;
    }

    public void eliminarColumna(String columna) {
        Nodo tmp = first;
        HashMap hashmap = null;
        while (tmp != null) {
            hashmap = (HashMap) tmp.getDato();
            hashmap.remove(columna);
        }
    }
    public Lista() {
    }

}
