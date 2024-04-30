package ast.Expresiones;

import ast.LocatedNode;

public class True extends LocatedNode implements E{
    public True(int fila, int columna) {
        super(fila, columna);
    }



    public String toString() {
        return "TRUE";
    }

}

