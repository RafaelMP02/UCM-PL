package ast.Expresiones;

import ast.NodoAST;

public class EError implements E {

    @Override
    public String toString() {
        return NodoAST.ERROR_STR;
    }

}
