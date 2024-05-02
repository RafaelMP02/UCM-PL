package ast.Tipos;

import ast.Expresiones.Num;
import ast.Tipos.Tipado.TiposEnum;

public class Array extends TipoParametrizable{
    Num n;

    public Array(NodoTipo t, Num n) {
        super(t);
        this.n = n;
    }

    public String toString() {
        return tipo.toString() + "[" + n + "]";
    }

    @Override
    public TiposEnum typeToEnum() {
        return TiposEnum.ARRAY;
    }

}
