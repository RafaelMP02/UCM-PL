package ast.FuncionesPrimitivas;


import ast.Expresiones.E;
import ast.KindE;
import ast.NodeKind;

public class New extends E {

    public New(){}

    public KindE kind() {
        return KindE.New;
    }

    public NodeKind nodeKind() {
        return NodeKind.NEW;
    }

    public String toString() {
        return "NEW";
    }
}
