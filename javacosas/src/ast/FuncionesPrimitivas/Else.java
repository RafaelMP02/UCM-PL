package ast.FuncionesPrimitivas;

import ast.GeneracionCodigo.Comp;
import ast.NodoAST;

import java.util.Set;

import ast.Metaoperadores.Ambito;
import ast.Tipos.NodoTipo;
import ast.Tipos.Tipado;
import ast.Vinculacion.Vinculacion;

public class Else implements NodoAST {

    private Ambito ambito;
    private int columna;
    private int fila;

    public Else(Ambito amb, int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        this.ambito = amb;
    }

    @Override
    public int getFila() {
        return this.fila;
    }

    @Override
    public int getColumna() {
        return this.columna;
    }

    public String toString() {
        return "ELSE" + ambito.toString();
    }

    @Override
    public void bind(Vinculacion vinc) {
        ambito.bind(vinc);
    }

    @Override
    public Set<NodoTipo> type(Set<NodoTipo> tiposEsperados) {
        ambito.type(Tipado.enumToTipo(Tipado.TIPOS_INSTR));
        return null;
    }


    public String codeI(Comp hcon) {
        return ambito.codeI(hcon);

    }

    @Override
    public String codeFunc(Comp hcon){
        return ambito.codeFunc(hcon);
    }
}
