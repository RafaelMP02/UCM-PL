package ast.Tipos;

import ast.Identificador;
import ast.NodeKind;

public class Struct extends Tipo{
    Identificador iden;

    public Struct(Identificador iden) {
        this.iden = iden;
    }
    public String toString() {
        return "Struct " + iden.toString();
    }
}
