package ast;

import ast.Vinculacion.Vinculacion;

public interface ASTNode {
    public static final String ERROR_STR = "ERROR";
    // public ?? type() // for the future
    public void bind(Vinculacion vinc); // for the future
    // public ?? generateCode() // for the future
    public NodeKind nodeKind();
    public String toString();
}
