package ast.Operadores;

import ast.KindE;

public abstract class Operador {
    /* Los operadores sólo son atributos de expresiones, no se les considera nodos. Sin embargo, necesitan algunos métodos de los ASTNode. */
    public KindE exp;

    public KindE kind() {return exp;}
    public abstract String toString();
}
