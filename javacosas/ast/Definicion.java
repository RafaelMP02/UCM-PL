package ast;

public abstract class Definicion extends Instruccion{
    public NodeKind nodeKind() {
        return NodeKind.DEFINICION;
    }
}
