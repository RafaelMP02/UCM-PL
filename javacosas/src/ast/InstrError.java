package ast;

import java.util.Set;

import ast.Metaoperadores.Asignacion;
import ast.Tipos.NodoTipo;
import ast.Tipos.Tipado;

public class InstrError  extends Asignacion implements Programa {
    /* Nodo de error para las instrucciones.*/
    public InstrError() {
        super( null, null);
    }

    @Override
    public String toString() {
        return NodoAST.ERROR_STR;
    }

    @Override
    public Set<NodoTipo> type(Set<NodoTipo> tiposEsperados) {
        return Tipado.conjuntoError();
    }

}
