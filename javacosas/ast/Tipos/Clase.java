package ast.Tipos;

import ast.Expresiones.Identificador;
import ast.Tipos.Tipado.TiposEnum;

public class Clase extends NodoTipo{
    Identificador iden;

    public Clase(Identificador iden) {
        super(TiposEnum.CLASE);
        this.iden = iden;
    }
    public String toString() {
        return "CLASE " + iden.toString();
    }
}
