package ast.Expresiones;

import ast.KindE;

public class True extends E{
    public True(){}


    public KindE kind() {
        return KindE.True;
    }

    public String toString() {
        return "TRUE";
    }

}

