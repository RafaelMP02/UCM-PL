package ast.FuncionesPrimitivas;


import ast.Expresiones.E;
import ast.Vinculacion.Vinculacion;
import ast.Instruccion;
import ast.LocatedNode;
import ast.NodeKind;

public class Return extends LocatedNode implements Instruccion {
    private E valor;

    public Return(E valor, int fila, int columna) {
        super(fila, columna);
        this.valor = valor;
    }

    public NodeKind nodeKind() {
        return NodeKind.RETURN;
    }

    public String toString(){
        return "RETURN " + valor.toString();
    }

    @Override
    public void bind(Vinculacion vinc) {
        valor.bind(vinc);
    }
}
