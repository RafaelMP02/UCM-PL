package ast.Expresiones;

import ast.KindE;

public class False extends E{
    public False(){}

    public KindE kind() {
        return KindE.False;
    }

    public String toString() {
        return "FALSE";
    }
}
