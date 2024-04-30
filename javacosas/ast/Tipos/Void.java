package ast.Tipos;

import ast.Tipos.Tipado.TiposEnum;

public class Void extends NodoTipo{
    public Void(){
        super(TiposEnum.VOID);
    }

    public String toString(){
        return "VOID";
    }
}
