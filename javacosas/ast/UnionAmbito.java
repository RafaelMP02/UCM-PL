package ast;

import ast.Metaoperadores.Ambito;
import ast.Vinculacion.Vinculacion;


public class UnionAmbito implements Programa {
    //conjunción de un ambito y el resto del programa
    private Ambito amb;
    private Programa P;

    public UnionAmbito(Ambito amb, Programa P) {
        this.amb = amb;
        this.P = P;
    }
    public NodeKind nodeKind() {
        return NodeKind.UNIONAMBITO;
    }

    public String toString() {
        return amb.toString() + P.toString();
    }
    @Override
    public void bind(Vinculacion vinc) {
        //TODO aquí nada no??
    }
}
