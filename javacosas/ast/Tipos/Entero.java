package ast.Tipos;

import ast.Tipos.Tipado.TiposEnum;

public class Entero extends NodoTipo{
    public Entero(){
        super(TiposEnum.ENTERO);
    }

    public String toString() {
        return "ENTERO";
    }
}
