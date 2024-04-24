package ast.Tipos;

import ast.Expresiones.Identificador;
import ast.Vinculacion.Vinculacion;

public class Clase extends Tipo{
    Identificador iden;

    public Clase(Identificador iden) {
        this.iden = iden;
    }
    public String toString() {
        return "CLASE " + iden.toString();
    }
    @Override
    public void bind(Vinculacion vinc) {}
}
