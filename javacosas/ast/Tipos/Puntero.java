package ast.Tipos;

import ast.Tipos.Tipado.TiposEnum;

public class Puntero extends NodoTipo{
    NodoTipo t;


    public Puntero(NodoTipo t) {
        super(TiposEnum.PUNTERO);
        this.t = t;
    }

    public String toString() {
        return t.toString() + "^";
    }
}
