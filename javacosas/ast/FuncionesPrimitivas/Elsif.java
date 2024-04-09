package ast.FuncionesPrimitivas;

import ast.ASTNode;
import ast.Expresiones.E;
import ast.Metaoperadores.Ambito;
import ast.NodeKind;

public class Elsif implements ASTNode {
    private Elsif siguienteIf = null;
    private Else siguienteElse  = null;

    //ambos o uno deben de ser nulos

    private Ambito Amb;
    private E cond;

    public Elsif(E cond, Ambito Amb, Elsif siguienteIf, Else siguienteElse) {
        this.cond = cond;
        this.Amb = Amb;
        this.siguienteIf = siguienteIf;
        this.siguienteElse = siguienteElse;
    }

    public NodeKind nodeKind() {
        return NodeKind.ELSIF;
    }

    public String toString() {
        if(siguienteIf != null) {
            return "ELSIF(" + cond.toString() + ")" + Amb.toString()  + siguienteIf.toString();
        } else if (siguienteElse != null) {
            return "ELSIF(" + cond.toString() + ")" + Amb.toString()  + siguienteElse.toString();
        } else {
            return "ELSIF(" + cond.toString() + ")" + Amb.toString();
        }
    }
}
