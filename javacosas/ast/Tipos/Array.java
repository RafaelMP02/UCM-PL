package ast.Tipos;

import ast.Expresiones.Num;
import ast.Vinculacion.Vinculacion;

public class Array extends Tipo{
    Tipo t;
    Num n;

    public Array(Tipo t, Num n) {
        this.t = t;
        this.n = n;
    }

    public String toString() {
        return t.toString() + "[" + n + "]";
    }

    @Override
    public void bind(Vinculacion vinc) {}
}
