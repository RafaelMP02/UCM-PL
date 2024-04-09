package ast.FuncionesPrimitivas;

import ast.ASTNode;
import ast.Metaoperadores.Ambito;
import ast.NodeKind;

public class Else implements ASTNode {

    private Ambito Amb;

    public Else( Ambito Amb) {
        this.Amb = Amb;
    }

    public NodeKind nodeKind() {
        return NodeKind.ELSE;
    }

    public String toString() {
        return "ELSE" + Amb.toString();
    }
}
