package ast.Tipos;

import ast.Identificador;

public class Clase extends Tipo{
    Identificador iden;

    public Clase(Identificador iden) {
        this.iden = iden;
    }
    public String toString() {
        return "Clase " + iden.toString();
    }
}
