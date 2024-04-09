package ast.Tipos;

import ast.Expresiones.Num;

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
}
