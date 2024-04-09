package ast.FuncionesPrimitivas;

import ast.Expresiones.E;
import ast.Metaoperadores.Ambito;
import ast.NodeKind;
import ast.Programa;

public class Switch extends Programa {
    private E cond;
    private Case caso;

    private Ambito amb;

    private Programa P;

    public Switch(E cond, Ambito amb, Programa P, Case caso) {
        this.cond = cond;
        this.amb = amb;
        this.P = P;
        this.caso = caso;
    }

    public NodeKind nodeKind() {
        return NodeKind.SWITCH;
    }


    public String toString() {
        if(cond != null) {
            if (caso != null) {
                return "SWITCH(" + cond.toString() + "){" + caso.toString() + "DEFAULT" + amb.toString() + "}" + P.toString();
            } else {
                return "SWITCH(" + cond.toString() + "){" + "DEFAULT" + amb.toString() + "}" + P.toString();
            }
        } else {
             return "SWITCH(#ERROR#)" + P.toString();
        }
    }
}
