package ast.Metaoperadores;

import ast.Instruccion;
import ast.NodeKind;
import ast.Programa;

public class PuntoYComa extends Programa {
    private Instruccion inst;
    private Programa prog;

    public PuntoYComa(Instruccion inst, Programa prog) {
       this.inst = inst;
       this.prog = prog;
    }

    public NodeKind nodeKind() {
        return NodeKind.PUNTOYCOMA;
    }

    public String toString() {
        return inst.toString() + " puntoYcoma " + prog.toString();
    }
}
