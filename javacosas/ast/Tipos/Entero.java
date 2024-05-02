package ast.Tipos;

import ast.Tipos.Tipado.TiposEnum;

public class Entero implements NodoTipo{
    public Entero(){
    }

    public String toString() {
        return "ENTERO";
    }
    
    @Override
    public TiposEnum typeToEnum() {
        return TiposEnum.ENTERO;
    }
}
