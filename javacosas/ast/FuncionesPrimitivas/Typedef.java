package ast.FuncionesPrimitivas;

import ast.Identificador;
import ast.Instruccion;
import ast.NodeKind;
import ast.Tipos.Tipo;

public class Typedef extends Instruccion {

    private Tipo t;
    private Identificador id;

    public Typedef(Tipo t, Identificador id) {
        this.t = t;
        this.id = id;
    }

    public NodeKind nodeKind() {
        return NodeKind.TYPEDEF;
    }

    public String toString() {
        return "TYPEDEF " + t.toString() + " " + id.toString();
    }
}
