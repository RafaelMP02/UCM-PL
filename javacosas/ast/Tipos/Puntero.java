package ast.Tipos;

import ast.Tipos.Tipado.TiposEnum;

public class Puntero extends TipoParametrizable{


    public Puntero(NodoTipo t) {
        super(t);
    }

    public String toString() {
        return tipo.toString() + "^";
    }

    @Override
    public TiposEnum typeToEnum() {
        return TiposEnum.PUNTERO;
    }
}
