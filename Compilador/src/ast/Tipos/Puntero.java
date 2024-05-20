package ast.Tipos;

import ast.Tipos.Tipado.TiposEnum;

public class Puntero extends TipoParametrizable{

    public Puntero(NodoTipo t) {
        super(t);
    }

    public Puntero() {
        super();
    }

    public String toString() {
        return tipo.toString() + "*";
    }

    @Override
    public TiposEnum typeToEnum() {
        return TiposEnum.PUNTERO;
    }

    @Override
    public int getTam(){ return 1;}
}
