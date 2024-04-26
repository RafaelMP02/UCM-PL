package ast.Metaoperadores;

import ast.ASTNode;
import ast.NodeKind;
import ast.Programa;
import ast.Vinculacion.Vinculacion;

public class Ambito implements ASTNode {
    private Programa programa;

    public Ambito(Programa prog) {
        this.programa = prog;
    }

    public NodeKind nodeKind() {
        return NodeKind.AMBITO;
    }

    public String toString() {
        return "{" + programa.toString() + "}";
    }

    @Override
    public void bind(Vinculacion vinc) {
        programa.bind(vinc);
    }
}
