package ast;

public interface Definicion extends Instruccion {
    default TiposDeNodos nodeKind() {
        return TiposDeNodos.DEFINICION;
    }
}
