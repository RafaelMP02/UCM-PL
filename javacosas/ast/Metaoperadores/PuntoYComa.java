package ast.Metaoperadores;

import java.util.Set;

import ast.Instruccion;
import ast.TiposDeNodos;
import ast.Tipos.NodoTipo;
import ast.Programa;
import ast.Vinculacion.Vinculacion;

public class PuntoYComa implements Programa {
    private Instruccion inst;
    private Programa prog;

    public PuntoYComa(Instruccion inst, Programa prog) { //TODO no conviene mejor que esta ser algo como InstruccionPyComa y que la clase PYComa sea solo el operador?
       this.inst = inst;
       this.prog = prog;
    }

    public TiposDeNodos nodeKind() {
        return TiposDeNodos.PUNTOYCOMA;
    }

    public String toString() {
        return inst.toString() + "; " + prog.toString();
    }

    @Override
    public void bind(Vinculacion vinc) {
        inst.bind(vinc);
        prog.bind(vinc);
    }

    @Override
    public Set<NodoTipo> type() {
        inst.type();
        prog.type();
        return null;
    }
}
