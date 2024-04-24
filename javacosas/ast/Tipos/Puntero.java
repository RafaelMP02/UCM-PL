package ast.Tipos;

import ast.Vinculacion.Vinculacion;

public class Puntero extends Tipo{
    Tipo t;


    public Puntero(Tipo t) {
        this.t = t;
    }

    public String toString() {
        return t.toString() + "*";
    }

    @Override
    public void bind(Vinculacion vinc) {}
}
