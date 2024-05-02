package ast.FuncionesPrimitivas;


import ast.Expresiones.E;
import ast.Tipos.NodoTipo;

import java.util.Set;

import ast.LocatedNode;
import ast.TiposDeNodos;

public class New extends LocatedNode implements E { //New no debería ser un metaoperador??

    public New(int fila, int columna) {
        super(fila, columna);
    }

    public TiposDeNodos nodeKind() {
        return TiposDeNodos.NEW;
    }

    public String toString() {
        return "NEW";
    }

    @Override
    public Set<NodoTipo> type() {
        return null; //FIXME esto está bien??
    }

}
