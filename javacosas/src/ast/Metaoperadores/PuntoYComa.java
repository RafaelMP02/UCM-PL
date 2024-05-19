package ast.Metaoperadores;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;

import ast.Declaracion;
import ast.GeneracionCodigo.Comp;
import ast.Instruccion;
import ast.Tipos.NodoTipo;
import ast.Tipos.Tipado;
import ast.Programa;
import ast.Vinculacion.Vinculacion;

public class PuntoYComa implements Programa {
    private Instruccion inst;
    private Programa prog;

    public PuntoYComa(Instruccion inst, Programa prog) {
       this.inst = inst;
       this.prog = prog;
    }

    public String toString() {
        return inst.toString() + "; " + prog.toString();
    }

    @Override
    public void bindFunc(Vinculacion vinc) {
        inst.bindFunc(vinc);
        prog.bindFunc(vinc);
    }

    @Override
    public void bind(Vinculacion vinc) {
        inst.bind(vinc);
        prog.bind(vinc);
    }

    @Override
    public Set<NodoTipo> type(Set<NodoTipo> tipoEsperado) {
        /* Tipa esta instrucción y las siguientes y devuelve el conjunto de todos los tipos. */
        return Tipado.unirConjuntos(inst.type(tipoEsperado), prog.type(tipoEsperado));
    }

    @Override
    public int getFila() {
        return inst.getFila();
    }

    @Override
    public int getColumna() {
        return inst.getColumna();
    }



    @Override
    public String codeI(Comp hcom) {
        return inst.codeI(hcom) + prog.codeI(hcom);
    }

    @Override
    public String codeFunc(Comp hcom) {
        return inst.codeFunc(hcom) + prog.codeFunc(hcom);
    }

    @Override
    public void recoleccionAtributos(LinkedHashMap<String,LinkedHashSet<Declaracion>> mapa_actual) {
        inst.recoleccionAtributos(mapa_actual);
        prog.recoleccionAtributos(mapa_actual);

    }
}
