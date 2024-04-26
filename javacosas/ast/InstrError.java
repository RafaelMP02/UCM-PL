package ast;

import ast.Expresiones.EError;
import ast.Metaoperadores.Asignacion;

public class InstrError  extends Asignacion implements Programa{

    public InstrError() {
        super(new EError(), new EError());
    }

    @Override
    public String toString() {
        return ASTNode.ERROR_STR;
    }

}
