package ast;


public class Vacio implements Programa{

    public Vacio(){}
    public TiposDeNodos nodeKind() {
        return TiposDeNodos.VACIO;
    }
    public String toString() {
        return "";
    }
}
