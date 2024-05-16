package ast;

import ast.GeneracionCodigo.Comp;

public interface Instruccion extends NodoAST {
    /* Interfaz para todas las instrucciones que terminene en ";" y no abran ámbito */
    public  String codeI(Comp hcom);
}
