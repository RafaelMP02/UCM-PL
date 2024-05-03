package ast.Tipos;

import ast.Tipos.Tipado.TiposEnum;

public class V0id implements NodoTipo{
    public V0id(){
    }

    public String toString(){
        return "VOID";
    }

    @Override
    public TiposEnum typeToEnum() {
        return TiposEnum.VOID;
    }
}
