package ast;

import ast.Expresiones.EError;
import ast.Metaoperadores.Asignacion;

public class InstrError  extends Asignacion implements Programa{
    /* Nodo de error para las instrucciones. Extiende de Asignacion para que en el "for" pueda detectar error en cada una de ellas.*/
    public InstrError() {
        super(new EError(), new EError());
    }

    @Override
    public String toString() {
        return NodoAST.ERROR_STR;
    }

}
