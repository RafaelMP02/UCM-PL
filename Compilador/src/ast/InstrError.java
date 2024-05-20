package ast;

import java.util.Set;

import ast.GeneracionCodigo.Comp;
import ast.Tipos.NodoTipo;
import ast.Tipos.Tipado;

public class InstrError  extends Declaracion implements Programa {
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

    @Override
    public String codeI(Comp hcom) {
        // TODO lo dejamos sin implementar. Jamás se llamará a generación de código si hay un error.
        throw new UnsupportedOperationException("Unimplemented method 'codeI'");
    }

}
