package ast.FuncionesPrimitivas;

import ast.Expresiones.Identificador;

import java.util.Set;

import ast.Instruccion;
import ast.LocatedNode;
import ast.TiposDeNodos;
import ast.Tipos.NodoTipo;
import ast.Vinculacion.Vinculacion;

public class Typedef extends LocatedNode implements Instruccion {
        // FIXME quitar el typedef
    private NodoTipo t;
    private Identificador id;

    public Typedef(NodoTipo t, Identificador id, int fila, int columna) {
        super(fila, columna);
        this.t = t;
        this.id = id;
    }

    public TiposDeNodos nodeKind() {
        return TiposDeNodos.TYPEDEF;
    }

    public String toString() {
        return "TYPEDEF " + t.toString() + " " + id.toString();
    }

    @Override
    public void bind(Vinculacion vinc) {

    }

    @Override
    public Set<NodoTipo> type() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'type'");
    }
}
