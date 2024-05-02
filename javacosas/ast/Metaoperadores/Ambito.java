package ast.Metaoperadores;

import java.util.Set;

import ast.NodoAST;
import ast.TiposDeNodos;
import ast.Tipos.NodoTipo;
import ast.Programa;
import ast.Vinculacion.Vinculacion;

public class Ambito implements NodoAST {
    private Programa programa;

    public Ambito(Programa prog) {
        this.programa = prog;
    }

    public TiposDeNodos nodeKind() {
        return TiposDeNodos.AMBITO;
    }

    public String toString() {
        return " {" + programa.toString() + "}";
    }

    @Override
    public void bind(Vinculacion vinc) {
        programa.bind(vinc);
    }

    @Override
    public Set<NodoTipo> type() {
        programa.type();
        return null;
    }
}
