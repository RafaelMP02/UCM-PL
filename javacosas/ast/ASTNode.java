package ast;

import ast.Vinculacion.Vinculacion;

public interface ASTNode {
    // public ?? type() // for the future
    public void bind(Vinculacion vinc); // for the future
    // public ?? generateCode() // for the future
    public NodeKind nodeKind();
    public String toString();
}
