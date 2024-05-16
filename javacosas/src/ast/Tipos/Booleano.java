package ast.Tipos;

import ast.Tipos.Tipado.TiposEnum;

public class Booleano implements NodoTipo{
    private int fila;
    private int columna;

    public Booleano(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
    }
    public Booleano() {
        //TODO Auto-generated constructor stub
    }
    public String toString() {
        return "BOOL";
    }

    @Override
    public TiposEnum typeToEnum() {
        return TiposEnum.BOOLEANO;
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
