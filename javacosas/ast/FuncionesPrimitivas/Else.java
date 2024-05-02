package ast.FuncionesPrimitivas;

import ast.NodoAST;

import java.util.Set;

import ast.LocatedNode;
import ast.Metaoperadores.Ambito;
import ast.Tipos.NodoTipo;
import ast.Vinculacion.Vinculacion;
import ast.TiposDeNodos;

public class Else extends LocatedNode implements NodoAST {

    private Ambito ambito;

    public Else(Ambito amb, int fila, int columna) {
        super(fila, columna);
        this.ambito = amb;
    }

    public TiposDeNodos nodeKind() {
        return TiposDeNodos.ELSE;
    }

    public String toString() {
        return "ELSE" + ambito.toString();
    }

    @Override
    public void bind(Vinculacion vinc) {
        vinc.abreBloque();
        ambito.bind(vinc);
        vinc.cierraBloque();
    }

    @Override
    public Set<NodoTipo> type() {
        ambito.type();
        return null;
    }
}
