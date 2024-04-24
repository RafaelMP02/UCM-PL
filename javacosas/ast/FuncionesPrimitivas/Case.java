package ast.FuncionesPrimitivas;

import ast.ASTNode;
import ast.Expresiones.Num;
import ast.Metaoperadores.Ambito;
import ast.NodeKind;


public class Case implements ASTNode {
    private Num cond;
    private Case caso;
    private Ambito amb;

    public Case(Num cond, Ambito amb, Case caso) {
        this.cond = cond;
        this.amb= amb;
        this.caso = caso;
    }

    public NodeKind nodeKind() {
        return NodeKind.Case;
    }
    public String toString() {
        if(caso != null) {
            return "CASE " + cond.toString() + amb.toString() + caso.toString();
        } else {
            return "CASE " + cond.toString() + amb.toString();
        }
    }
}
