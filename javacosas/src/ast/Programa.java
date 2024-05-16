package ast;

import ast.GeneracionCodigo.Comp;

public interface Programa extends NodoAST{
    /* Interfaz para todas aquellas instrucciones que abran un ámbito */
    public String codeI(Comp hcom);
}
