package ast;


public abstract class LocatedNode{
    /* Clase abstracta para aquellos tokens de los que necesitemos la fila y columna al recorrer el árbol */
    protected int fila, columna;

    public LocatedNode (int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
    }
}
