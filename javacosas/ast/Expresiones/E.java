package ast.Expresiones;

import ast.Metaoperadores.CabecerAsig;
import ast.NodeKind;

public interface E extends CabecerAsig {
    default NodeKind nodeKind() {return NodeKind.EXPRESION;}
}
