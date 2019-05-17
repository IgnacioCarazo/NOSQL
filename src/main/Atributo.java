package main;

public class Atributo {
    public double valueDouble;
    public int valueInt;
    public String valueString;
    public String tipo;
    public Boolean llave;
    public Boolean esJoin;
    public String esquemaJoin;
    public Atributo columnaJoin;

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
