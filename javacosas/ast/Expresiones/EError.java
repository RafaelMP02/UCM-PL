package ast.Expresiones;

import java.util.Set;

import ast.NodoAST;
import ast.Tipos.NodoTipo;
import ast.Tipos.Tipado;

public class EError implements E {

    @Override
    public String toString() {
        return NodoAST.ERROR_STR;
    }

    @Override
    public Set<NodoTipo> type() {
        return Tipado.conjuntoError();
    }

}
