package ast.Tipos;

import ast.Tipos.Tipado.TiposEnum;

public class Entero implements NodoTipo{
    private int fila;
    private int columna;

    public Entero(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
    }

    public Entero() {
        this.fila = -1;
        this.columna = -1;
    }

    public String toString() {
        return "ENTERO";
    }
    
    @Override
    public TiposEnum typeToEnum() {
        return TiposEnum.ENTERO;
    }

    @Override
    public int getFila() {
        return this.fila;
    }

    @Override
    public int getColumna() {
        return this.columna;
    }

    @Override
    public int getTam(){
        return 1;
    }
}
