package ast.Expresiones;

import java.util.Set;

import ast.NodoAST;
import ast.Tipos.NodoTipo;
import ast.Tipos.Tipado;
import ast.Vinculacion.Vinculacion;

public class EError extends Identificador {
    public EError() {
        super(NodoAST.ERROR_STR);
        asignable = true;
    }

    @Override
    public String toString() {
        return NodoAST.ERROR_STR;
    }

    @Override
    public void bind(Vinculacion vinc) {
    }

    @Override
    public Set<NodoTipo> type(Set<NodoTipo> tiposEsperados) {
        return Tipado.conjuntoError();
    }

}
