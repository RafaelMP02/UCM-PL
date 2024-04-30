package ast;

import ast.Metaoperadores.Ambito;
import ast.Vinculacion.Vinculacion;


public class UnionAmbito implements Programa {
    /* Conjunción de un ámbito y el resto del programa */
    private Ambito ambito;
    private Programa programa;

    public UnionAmbito(Ambito amb, Programa p) {
        this.ambito = amb;
        this.programa = p;
    }
    public TiposDeNodos nodeKind() {
        return TiposDeNodos.UNIONAMBITO;
    }

    public String toString() {
        return ambito.toString() + programa.toString();
    }
    @Override
    public void bind(Vinculacion vinc) {
        ambito.bind(vinc);
        programa.bind(vinc);
    }
}
