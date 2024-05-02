package ast.FuncionesPrimitivas;

import ast.NodoAST;

import java.util.Set;

import ast.LocatedNode;
import ast.Expresiones.Num;
import ast.Metaoperadores.Ambito;
import ast.Tipos.NodoTipo;
import ast.Vinculacion.Vinculacion;
import ast.TiposDeNodos;


public class Case extends LocatedNode implements NodoAST {
    private Num cond;
    private Case caso;
    private Ambito ambito;

    public Case(Num cond, Ambito amb, Case caso, int fila, int columna) {
        super(fila, columna);
        this.cond = cond;
        this.ambito= amb;
        this.caso = caso;
    }

    public TiposDeNodos nodeKind() {
        return TiposDeNodos.CASE;
    }
    public String toString() {
        if(caso != null) {
            return "CASE " + cond.toString() + ambito.toString() + caso.toString();
        } else {
            return "CASE " + cond.toString() + ambito.toString();
        }
    }

    @Override
    public void bind(Vinculacion vinc) {
        vinc.abreBloque();
        ambito.bind(vinc);
        vinc.cierraBloque();
        if (caso != null)
            caso.bind(vinc);
    }

    @Override
    public Set<NodoTipo> type() {
        ambito.type();
        caso.type();
        return null;
    }
}
