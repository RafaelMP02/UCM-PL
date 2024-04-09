package ast.Expresiones;

import ast.Identificador;
import ast.KindE;

public class ECampo extends E{
    private E struct;

    private Identificador campo;

    public ECampo(E struct, Identificador campo) {
        this.struct = struct;
        this.campo = campo;
    }

    public KindE kind() {
        return KindE.AccesoCampo;
    }
    public String toString() {
        return struct.toString() + "." + campo.toString();
    }
}
