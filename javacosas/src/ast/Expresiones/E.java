package ast.Expresiones;

import ast.GeneracionCodigo.Comp;
import ast.Metaoperadores.CabecerAsig;
import ast.Tipos.NodoTipo;


public abstract class E implements CabecerAsig {
    protected int fila, columna;
    protected boolean asignable; //Indica si esta expresión puede ser el operando izquierdo de una asignación
    protected KindE exp;
    protected NodoTipo tipo;

    public E (int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
    }

    public E () {
        this.fila = -1;
        this.columna = -1;
    }

    public int getFila() {return fila;}
    public int getColumna() {return columna;}
    @Override
    public boolean esAsignable() { return asignable;}

    public abstract String codeE(Comp hcon);

    @Override
    public NodoTipo tip(){ return tipo;}

}
