package ast.Operadores;

import ast.KindE;

public abstract class Operador {
    public KindE exp;

    public KindE kind() {return exp;}
    public abstract String toString();
}
