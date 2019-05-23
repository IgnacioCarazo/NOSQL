package Arboles;

import java.util.HashMap;

class Nodo{

    int id,factorEqulibrio;
    HashMap hashMap;


    Nodo hijoIzq, hijoDer;

    public Nodo(int id,HashMap hashMap) {
        this.id = id;
        this.hashMap = hashMap;
    }



}


public class ArbolAVL {

    public Nodo raiz;
    public boolean duplicado;

    public ArbolAVL() {
    }

    public HashMap buscarElemento(int id) {
        Nodo nodo = buscarElemento(raiz, id);
        HashMap instancia = nodo.hashMap;
        return instancia;
    }

    public Nodo buscarElemento(Nodo current, int id) {
        if (current == null) {
            return null;
        } else if (current.id == id) {
            return current;
        } else if (current.id < id) {
            return buscarElemento(current.hijoDer, id);
        } else {
            return buscarElemento(current.hijoIzq, id);
        }
    }

    public int obtenerFE(Nodo nodoEnCurso) {
        if (nodoEnCurso == null) {
            return -1;
        } else {
            return nodoEnCurso.factorEqulibrio;
        }
    }


    public Nodo rotacionIzq(Nodo nodo) {
        Nodo aux = nodo.hijoIzq;
        nodo.hijoIzq = aux.hijoDer;
        aux.hijoDer = nodo;
        nodo.factorEqulibrio = Math.max(obtenerFE(nodo.hijoIzq), obtenerFE(nodo.hijoDer)) + 1;
        aux.factorEqulibrio = Math.max(obtenerFE(aux.hijoIzq), obtenerFE(aux.hijoDer)) + 1;
        return aux;
    }


    public Nodo rotacionDer(Nodo nodo) {
        Nodo aux = nodo.hijoDer;
        nodo.hijoDer = aux.hijoIzq;
        aux.hijoIzq = nodo;
        nodo.factorEqulibrio = Math.max(obtenerFE(nodo.hijoIzq), obtenerFE(nodo.hijoDer)) + 1;
        aux.factorEqulibrio = Math.max(obtenerFE(aux.hijoIzq), obtenerFE(aux.hijoDer)) + 1;
        return aux;
    }

    public Nodo rotacionDobleIzq(Nodo nodo) {
        Nodo temporal;
        nodo.hijoIzq = rotacionDer(nodo.hijoIzq);
        temporal = rotacionIzq(nodo);
        return temporal;
    }

    public Nodo rotacionDobleDer(Nodo nodo) {
        Nodo temporal;
        nodo.hijoDer = rotacionIzq(nodo.hijoDer);
        temporal = rotacionDer(nodo);
        return temporal;
    }

    public Nodo insertar(Nodo nuevoNodo, Nodo subArbol) {
        Nodo nuevoPadre = subArbol;
        if (nuevoNodo.id < subArbol.id) {
            if (subArbol.hijoIzq == null) {
                subArbol.hijoIzq = nuevoNodo;
            } else {
                subArbol.hijoIzq = insertar(nuevoNodo, subArbol.hijoIzq);
                if (obtenerFE(subArbol.hijoIzq) - obtenerFE(subArbol.hijoDer) == 2) {
                    if (nuevoNodo.id < subArbol.hijoIzq.id) {
                        nuevoPadre = rotacionIzq(subArbol);
                    } else {
                        nuevoPadre = rotacionDobleIzq(subArbol);
                    }
                }
            }
        } else if (nuevoNodo.id > subArbol.id) {
            if (subArbol.hijoDer == null) {
                subArbol.hijoDer = nuevoNodo;
            } else {
                subArbol.hijoDer = insertar(nuevoNodo, subArbol.hijoDer);
                if (obtenerFE(subArbol.hijoDer) - obtenerFE(subArbol.hijoIzq) == 2) {
                    if (nuevoNodo.id > subArbol.hijoDer.id) {
                        nuevoPadre = rotacionDer(subArbol);
                    } else {
                        nuevoPadre = rotacionDobleDer(subArbol);
                    }
                }
            }
        } else {
            System.out.println("DUPLICADO");
            duplicado();

        }
        if (subArbol.hijoIzq == null && subArbol.hijoDer != null) {
            subArbol.factorEqulibrio = subArbol.hijoDer.factorEqulibrio+1;
        } else if (subArbol.hijoDer == null && subArbol.hijoIzq != null) {

            subArbol.factorEqulibrio = subArbol.factorEqulibrio +1;
        }else{
            subArbol.factorEqulibrio = Math.max(obtenerFE(subArbol.hijoIzq),obtenerFE(subArbol.hijoDer))+1;
        }
        return nuevoPadre;
    }

    public boolean duplicado(){
        return this.duplicado = true;
    }

    public void setDuplicado(boolean duplicado) {
        this.duplicado = duplicado;
    }

    public void insertar(int id, HashMap hashMap){
        Nodo nuevo = new Nodo(id, hashMap);
        if(raiz==null){
            raiz=nuevo;
        }else {
            raiz=insertar(nuevo,raiz);

        }
    }

    public void inOrden(){
        inOrden(raiz);
    }

    public void inOrden(Nodo enCurso){
        if(enCurso!=null){
            inOrden(enCurso.hijoIzq);
            System.out.println(enCurso.id);
            inOrden(enCurso.hijoDer);
        }
    }

    public void preOrden(){
        preOrden(raiz);
    }

    public void preOrden(Nodo enCurso){
        if(enCurso!=null){
            System.out.println(enCurso.id);
            preOrden(enCurso.hijoIzq);
            preOrden(enCurso.hijoDer);
        }
    }

    public void postOrden(){
        postOrden(raiz);
    }

    public void postOrden(Nodo enCurso){
        if(enCurso!=null){
            postOrden(enCurso.hijoIzq);
            postOrden(enCurso.hijoDer);
            System.out.println(enCurso.id);
        }
    }

    public static void main(String[] args) {
        ArbolAVL arbol = new ArbolAVL();

        arbol.insertar(5,new HashMap());
        arbol.insertar(8,new HashMap());
        arbol.insertar(5,new HashMap());


        arbol.preOrden();
    }

}
