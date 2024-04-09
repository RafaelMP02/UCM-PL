package ast.FuncionesPrimitivas;

import ast.Expresiones.E;
import ast.Metaoperadores.Ambito;
import ast.NodeKind;
import ast.Programa;


public class While extends Programa {
    E cond;
    Ambito amb;
    Programa P;

    public While(E cond, Ambito amb, Programa P){
        this.cond = cond;
        this.amb = amb;
        this.P = P;
    }

    public NodeKind nodeKind() {
        return NodeKind.WHILE;
    }
    public String toString() {
        if( cond != null) {
            return "WHILE(" + cond.toString() + ")" + amb.toString() + P.toString();
        } else {
            return "WHILE(#ERROR#)" + amb.toString() + P.toString();
        }
    }
}
