package ast.FuncionesPrimitivas;

import ast.ASTNode;
import ast.Expresiones.E;
import ast.Instruccion;
import ast.NodeKind;

public class Prints extends Instruccion {
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
