package ast.Tipos;

import ast.TiposDeNodos;
import ast.Tipos.Tipado.TiposEnum;

public abstract class NodoTipo implements Parametrico {
    /* Nodo del AST de un tipo */
    protected TiposEnum tipo;
    public NodoTipo (TiposEnum tipo) {
        this.tipo = tipo;
    }
    public TiposDeNodos nodeKind() {
        return TiposDeNodos.TIPO;
    }
}
