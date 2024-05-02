package ast.Operadores;

import java.util.List;
import java.util.Set;

import ast.Tipos.Tipado.TiposEnum;

public abstract class Operador {
    /* Los operadores sólo son atributos de expresiones, no se les considera nodos. Sin embargo, necesitan algunos métodos de los ASTNode. */
    protected Set<List<TiposEnum>> tipado;
    protected String opString;
    protected int fila;
    protected int columna;

    public Operador(int fila, int columna) {
        this.fila = fila;
        this. columna = columna;
    }

    public abstract void inicializarTipado();
    public abstract String toString();
    public Set<List<TiposEnum>> getTipado() {return tipado;}
    public int getFila() { return columna;}
    public int getColumna() {return fila;}
}
