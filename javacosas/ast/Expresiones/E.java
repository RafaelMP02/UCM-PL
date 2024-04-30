package ast.Expresiones;

import ast.Metaoperadores.CabecerAsig;
import ast.TiposDeNodos;

public interface E extends CabecerAsig {
    default TiposDeNodos nodeKind() {return TiposDeNodos.EXPRESION;}
}
