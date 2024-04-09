package ast.FuncionesPrimitivas;


import ast.Expresiones.E;
import ast.Instruccion;
import ast.NodeKind;

public class Return extends Instruccion {
    private E valor;

    public Return(E valor) {
        this.valor = valor;
    }

    public NodeKind nodeKind() {
        return NodeKind.RETURN;
    }

    public String toString(){
        return "RETURN " + valor.toString();
    }
}
