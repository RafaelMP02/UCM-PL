package ast.FuncionesPrimitivas;


import ast.Expresiones.E;
import ast.Vinculacion.Vinculacion;
import ast.KindE;
import ast.LocatedNode;
import ast.NodeKind;

public class New extends LocatedNode implements E { //New no debería ser un metaoperador??

    public New(int fila, int columna) {
        super(fila, columna);
    }

    public KindE kind() {
        return KindE.New;
    }

    public NodeKind nodeKind() {
        return NodeKind.NEW;
    }

    public String toString() {
        return "NEW";
    }

    @Override
    public void bind(Vinculacion vinc) {}
}
