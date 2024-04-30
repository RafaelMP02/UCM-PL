package ast.Tipos;

import ast.Expresiones.Identificador;
import ast.Tipos.Tipado.TiposEnum;

public class Struct extends NodoTipo{
    Identificador iden;

    public Struct(Identificador iden) {
        super(TiposEnum.STRUCT);
        this.iden = iden;
    }
    public String toString() {
        return "STRUCT " + iden.toString();
    }
}
