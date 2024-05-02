package ast.Tipos;

import ast.Tipos.Tipado.TiposEnum;

public class TipoError implements NodoTipo{

    @Override
    public TiposEnum typeToEnum() {
        return TiposEnum.ERROR;
    }

}
