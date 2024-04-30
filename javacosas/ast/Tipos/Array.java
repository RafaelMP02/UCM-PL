package ast.Tipos;

import ast.Expresiones.Num;
import ast.Tipos.Tipado.TiposEnum;

public class Array extends NodoTipo{
    NodoTipo t; //Nodo Tipo del array
    Num n;

    public Array(NodoTipo t, Num n) {
        super(TiposEnum.ARRAY);
        this.t = t;
        this.n = n;
    }

    public String toString() {
        return t.toString() + "[" + n + "]";
    }

}
