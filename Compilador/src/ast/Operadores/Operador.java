package ast.Operadores;

import java.util.List;
import java.util.Set;

import ast.Expresiones.KindE;
import ast.Tipos.NodoTipo;

public abstract class Operador {
    /* Los operadores sólo son atributos de expresiones, no se les considera nodos. Sin embargo, necesitan algunos métodos de los ASTNode. */
    protected List<Set<NodoTipo>> tipado;
    protected String opString;
    protected int fila;
    protected int columna;

    protected KindE exp;

    public Operador(int fila, int columna) {
        this.fila = fila;
        this. columna = columna;
        this.inicializarTipado();
    }

    public abstract void inicializarTipado();
    public abstract String toString();
    public List<Set<NodoTipo>> getTipado() {return tipado;}
    public int getFila() { return fila;}
    public int getColumna() {return columna;}

    public KindE getExp() {
        return exp;
    }
}
