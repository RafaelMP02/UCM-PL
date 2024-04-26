package ast.Expresiones;

import ast.ASTNode;
import ast.Vinculacion.Vinculacion;

public class EError implements E {

    @Override
    public void bind(Vinculacion vinc) {}
    
    @Override
    public String toString() {
        return ASTNode.ERROR_STR;
    }

}
