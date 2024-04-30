package ast.Tipos;

import ast.Tipos.Tipado.TiposEnum;

public class Booleano extends NodoTipo{
    public Booleano(){
        super(TiposEnum.BOOLEANO);
    }
    public String toString() {
        return "BOOL";
    }
}
