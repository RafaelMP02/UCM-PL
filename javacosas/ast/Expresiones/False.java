package ast.Expresiones;

import ast.KindE;
import ast.LocatedNode;
import ast.Vinculacion.Vinculacion;

public class False extends LocatedNode implements E{

    public False(int fila, int columna) {
        super(fila, columna);
    }

    public KindE kind() {
        return KindE.False;
    }

    public String toString() {
        return "FALSE";
    }

    @Override
    public void bind(Vinculacion vinc) {}
}
