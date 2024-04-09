package ast;

public class Vacio extends Programa{

    public Vacio(){}
    public NodeKind nodeKind() {
        return NodeKind.VACIO;
    }
    public String toString() {
        return "";
    }
}
