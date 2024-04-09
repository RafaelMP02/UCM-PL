package ast.Metaoperadores;

import ast.ASTNode;
import ast.Instruccion;
import ast.NodeKind;
import ast.Programa;

public class Ambito implements ASTNode {
    private Programa prog;

    public Ambito(Programa prog) {
        this.prog = prog;
    }

    public NodeKind nodeKind() {
        return NodeKind.AMBITO;
    }

    public String toString() {
        return "{" + prog.toString() + "}";
    }
}
