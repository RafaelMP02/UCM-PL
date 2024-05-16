package ast.Tipos;

import ast.Tipos.Tipado.TiposEnum;

public class Void implements NodoTipo{
    private int columna;
    private int fila;

    public Void () {
        this.fila = -1;
        this.columna = -1;
    }

    public Void (int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
    }

    public String toString(){
        return "VOID";
    }

    @Override
    public TiposEnum typeToEnum() {
        return TiposEnum.VOID;
    }

    @Override
    public int getFila() {
        return this.fila;
    }

    @Override
    public int getColumna() {
        return this.columna;
    }
}
