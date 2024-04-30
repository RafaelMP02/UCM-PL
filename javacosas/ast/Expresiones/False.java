package ast.Expresiones;

import ast.LocatedNode;
import ast.NodoAST;
import ast.Vinculacion.Vinculacion;

public class False extends LocatedNode implements E{

    public False(int fila, int columna) {
        super(fila, columna);
    }


    public String toString() {
        return "FALSE";
    }

}
