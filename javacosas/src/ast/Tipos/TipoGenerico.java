package ast.Tipos;

import ast.Tipos.Tipado.TiposEnum;

public class TipoGenerico implements NodoTipo {

    @Override
    public TiposEnum typeToEnum() {
        return TiposEnum.T;
    }

    @Override
    public int getFila() {
        return -1;
    }

    @Override
    public int getColumna() {
        return -1;
    }

    @Override
    public String toString() {
        return "T";
    }

}
