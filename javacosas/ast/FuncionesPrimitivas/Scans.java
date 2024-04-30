package ast.FuncionesPrimitivas;


import ast.Expresiones.Identificador;
import ast.Vinculacion.Vinculacion;
import ast.Instruccion;
import ast.LocatedNode;
import ast.TiposDeNodos;

public class Scans extends LocatedNode implements Instruccion {
    private Identificador id;

    public Scans(Identificador id, int fila, int columna) {
        super(fila, columna);
        this.id = id;
    }

    public TiposDeNodos nodeKind() {
        return TiposDeNodos.SCANS;
    }
    public String toString() {
        return "SCANS(" + id.toString() + ")";
    }

    @Override
    public void bind(Vinculacion vinc) {
        id.bind(vinc);
    }
}
