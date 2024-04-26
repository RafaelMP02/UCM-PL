package ast.FuncionesPrimitivas;

import ast.Expresiones.Identificador;
import ast.Instruccion;
import ast.LocatedNode;
import ast.NodeKind;
import ast.Tipos.Tipo;
import ast.Vinculacion.Vinculacion;

public class Typedef extends LocatedNode implements Instruccion {

    private Tipo t;
    private Identificador id;

    public Typedef(Tipo t, Identificador id, int fila, int columna) {
        super(fila, columna);
        this.t = t;
        this.id = id;
    }

    public NodeKind nodeKind() {
        return NodeKind.TYPEDEF;
    }

    public String toString() {
        return "TYPEDEF " + t.toString() + " " + id.toString();
    }

    @Override
    public void bind(Vinculacion vinc) {
        // FIXME está bien esto?
        vinc.insertaId(id.toString(), this);
    }
}
