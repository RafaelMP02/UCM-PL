package ast.Expresiones;

import ast.KindE;
import ast.LocatedNode;
import ast.Vinculacion.Vinculacion;

public class True extends LocatedNode implements E{
    public True(int fila, int columna) {
        super(fila, columna);
    }


    public KindE kind() {
        return KindE.True;
    }

    public String toString() {
        return "TRUE";
    }


    @Override
    public void bind(Vinculacion vinc) {}

}

