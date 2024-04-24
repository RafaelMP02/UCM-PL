package ast.Expresiones;

import ast.KindE;
import ast.Metaoperadores.CabecerAsig;
import ast.NodeKind;

public abstract class E implements CabecerAsig {
    public abstract KindE kind();
    public NodeKind nodeKind() {return NodeKind.EXPRESION;}
    public abstract String toString();

}
