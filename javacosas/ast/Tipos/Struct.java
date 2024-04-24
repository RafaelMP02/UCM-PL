package ast.Tipos;

import ast.Expresiones.Identificador;
import ast.Vinculacion.Vinculacion;

public class Struct extends Tipo{
    Identificador iden;

    public Struct(Identificador iden) {
        this.iden = iden;
    }
    public String toString() {
        return "STRUCT " + iden.toString();
    }
    @Override
    public void bind(Vinculacion vinc) {}
}
