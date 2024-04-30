package ast.Expresiones;

import java.util.List;

import ast.NodoAST;
import ast.LocatedNode;
import ast.Vinculacion.Vinculacion;

public class Identificador extends LocatedNode implements E {
    private String v;
    private List<NodoAST> vinculos;

    public Identificador(String v, int fila, int columna) {
        super(fila, columna);
        this.v = v;
    }
    public String num() {return v;}
    public String toString() {return v;}

    public void bind(Vinculacion vinc) {
        /* Los identificadores se encargan de buscarse en la tabla de símbolos. Solo se llamará a id.bind() cuando no sea una declaración de el id. */
        vinculos = vinc.buscaId(v, fila, columna);
    }
}

