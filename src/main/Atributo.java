package main;

import Arboles.ArbolAVL;
import Arboles.ArbolR_N;
import Arboles.BinaryTree;

public class Atributo {
    public double valueDouble;
    public Integer valueInt;
    public String valueString;

    public String tipo;
    public Boolean llave;

    public String tipoArbol;
    public ArbolAVL arbolAVL;
    public ArbolR_N arbolR_N;
    public BinaryTree binaryTree;

    public Boolean esJoin;
    public String esquemaJoin;
    public String columnaJoin;

    public boolean entrada;

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setLlave(Boolean llave) {
        this.llave = llave;
    }

    public void setValueDouble(double valueDouble) {
        this.valueDouble = valueDouble;
    }

    public void setValueInt(int valueInt) {
        this.valueInt = valueInt;
    }

    public void setValueString(String valueString) {
        this.valueString = valueString;
    }

    public double getValueDouble() {
        return valueDouble;
    }

    public int getValueInt() {
        return valueInt;
    }

    public String getValueString() {
        return valueString;
    }

    public Atributo() {
    }
}
