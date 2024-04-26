package ast;

import ast.Vinculacion.Vinculacion;

public class Vacio implements Programa{

    public Vacio(){}
    public NodeKind nodeKind() {
        return NodeKind.VACIO;
    }
    public String toString() {
        return "";
    }
    @Override
    public void bind(Vinculacion vinc) {}
}
