package ast;

public abstract class Definicion implements Instruccion {
    public NodeKind nodeKind() {
        return NodeKind.DEFINICION;
    }
}
