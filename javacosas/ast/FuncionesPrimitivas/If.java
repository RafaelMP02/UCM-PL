package ast.FuncionesPrimitivas;

import ast.Expresiones.E;
import ast.Metaoperadores.Ambito;
import ast.NodeKind;
import ast.Programa;

public class If extends Programa {

    private Elsif siguienteIf = null;
    private Else siguienteElse  = null;

    //ambos o uno deben de ser nulos

    private Ambito Amb;
    private E cond;

    private Programa P;

    public If(E cond, Ambito Amb, Programa P,Elsif siguienteIf, Else siguienteElse) {
        this.cond = cond;
        this.Amb = Amb;
        this.siguienteIf = siguienteIf;
        this.siguienteElse = siguienteElse;
        this.P = P;
    }

    public NodeKind nodeKind() {
        return NodeKind.IF;
    }

    public String toString() {
        if(cond != null) {
            if (siguienteIf != null) {
                return "IF(" + cond.toString() + ")" + Amb.toString() + siguienteIf.toString() + P.toString();
            } else if (siguienteElse != null) {
                return "IF(" + cond.toString() + ")" + Amb.toString() + siguienteElse.toString() + P.toString();
            } else {
                return "IF(" + cond.toString() + ")" + Amb.toString() + P.toString();
            }
        } else {
            return "#ERROR#" + P.toString();
        }
    }
}
