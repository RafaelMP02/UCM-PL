package ast.Tipos;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import ast.TiposDeNodos;
import ast.Tipos.Tipado.TiposEnum;


public interface NodoTipo extends Parametrico {
    /* Nodo del AST de un tipo */

    public default TiposDeNodos nodeKind() {
        return TiposDeNodos.TIPO;
    }

    public TiposEnum typeToEnum();
    
    @Override
    public default Set<NodoTipo> type() {
        return new HashSet<NodoTipo>(Collections.singleton(this));
    }
    public default boolean admiteSobrecarga() { return false;}
}
