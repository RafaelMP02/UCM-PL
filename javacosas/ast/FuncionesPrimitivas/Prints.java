package ast.FuncionesPrimitivas;

import ast.Expresiones.E;
import ast.Instruccion;
import ast.NodeKind;

public class Prints implements Instruccion {
    private E valor;

    public Prints(E valor) {
        this.valor = valor;
    }

    public NodeKind nodeKind() {
        return NodeKind.PRINTS;
    }

    public String toString(){
        return "PRINTS(" + valor.toString() + ")";
    }
}
