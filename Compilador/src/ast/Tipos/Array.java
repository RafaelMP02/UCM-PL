package ast.Tipos;

import ast.Expresiones.Num;
import ast.Tipos.Tipado.TiposEnum;

public class Array extends TipoParametrizable{
    Num n;

    public Array(NodoTipo t, Num n, int fila, int columna) {
        super(t, fila, columna);
        this.n = n;
        this.tam = t.getTam()*n.num();
    }

    public Array(NodoTipo t, Num n) {
        super(t, -1, -1);
        this.n = n;
    }

    public Array(NodoTipo t) {
        super(t, -1, -1);
        this.n = new Num();
    }

    public Array(Num n) {
        super();
        this.n = n;
    }

    public Array() {
        super();
        this.n = new Num();
    }

    public String toString() {
        if (this.n.num() == -1)
            return tipo.toString() +  "[]";
        else
            return tipo.toString() + "[" + n + "]";
    }

    @Override
    public TiposEnum typeToEnum() {
        return TiposEnum.ARRAY;
    }

    public int getN() {
        return  this.n.num();
    }

}
