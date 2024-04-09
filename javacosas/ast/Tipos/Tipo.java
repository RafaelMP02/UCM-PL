package ast.Tipos;

import ast.NodeKind;

public abstract class Tipo implements Parametrico {
    public NodeKind nodeKind() {
        return NodeKind.TIPO;
    }
}
