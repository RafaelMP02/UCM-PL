package ast.Tipos;

import ast.Tipos.Tipado.TiposEnum;

public class Booleano implements NodoTipo{
    public Booleano(){
    }
    public String toString() {
        return "BOOL";
    }

    @Override
    public TiposEnum typeToEnum() {
        return TiposEnum.BOOLEANO;
    }
}
