package ast;

import java.util.Set;

import ast.GeneracionCodigo.Comp;
import ast.Metaoperadores.Ambito;
import ast.Tipos.NodoTipo;
import ast.Tipos.Tipado;
import ast.Vinculacion.Vinculacion;


public class UnionAmbito implements Programa {
    /* Conjunción de un ámbito y el resto del programa */
    private Ambito ambito;
    private Programa programa;

    public UnionAmbito(Ambito amb, Programa p) {
        this.ambito = amb;
        this.programa = p;
    }

    public String toString() {
        return " " + ambito.toString() + programa.toString();
    }

    @Override 
    public void bindFunc(Vinculacion vinc) {
        programa.bindFunc(vinc);
    }

    @Override
    public void bind(Vinculacion vinc) {
        ambito.bind(vinc);
        programa.bind(vinc);
    }
    @Override
    public Set<NodoTipo> type(Set<NodoTipo> tiposEsperados) {
        return Tipado.unirConjuntos(ambito.type(tiposEsperados), programa.type(tiposEsperados));
    }

    @Override
    public int getFila() {
        return ambito.getFila();
    }

    @Override
    public int getColumna() {
        return ambito.getColumna();
    }

    @Override
    public String codeI(Comp hcom) {
        return ambito.codeI(hcom) + programa.codeI(hcom);
    }
}
