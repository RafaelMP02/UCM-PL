package ast.Operadores;

import java.util.List;
import java.util.Set;

import ast.Tipos.Tipado.TiposEnum;

public abstract class Operador {
    /* Los operadores sólo son atributos de expresiones, no se les considera nodos. Sin embargo, necesitan algunos métodos de los ASTNode. */
    protected List<Set<TiposEnum>> tipado;

    public abstract void inicializarTipado();
    public abstract String toString();
}
