package ast.FuncionesPrimitivas;


import ast.Identificador;
import ast.Instruccion;
import ast.NodeKind;

public class Scans implements Instruccion {
    private Identificador path;

    public Scans(Identificador path) {
        this.path = path;
    }

    public NodeKind nodeKind() {
        return NodeKind.SCANS;
    }
    public String toString() {
        return "SCANS(" + path.toString() + ")";
    }
}
