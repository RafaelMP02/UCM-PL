package ast.FuncionesPrimitivas;


import ast.Instruccion;
import ast.NodeKind;

public class New extends Instruccion {

    public New(){}

    public NodeKind nodeKind() {
        return NodeKind.NEW;
    }

    public String toString() {
        return "NEW";
    }
}
