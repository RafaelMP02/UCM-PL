package ast.Tipos;

import ast.NodoAST;
import ast.Tipos.Tipado.TiposEnum;

public class TipoError implements NodoTipo{

    @Override
    public TiposEnum typeToEnum() {
        return TiposEnum.ERROR;
    }

    @Override
    public String toString() {
        return NodoAST.ERROR_STR;
    }

    @Override
    public int getFila() {
        return -1;
    }

    @Override
    public int getColumna() {
        return -1;
    }

}
