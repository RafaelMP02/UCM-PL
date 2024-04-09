package ast;

import ast.Metaoperadores.CabecerAsig;
import ast.Tipos.Tipo;

public class Declaracion implements CabecerAsig, Instruccion {
    private Tipo t;

    private Identificador id;

    public Declaracion(Tipo t, Identificador id){
        this.t = t;
        this.id = id;
    }

    public NodeKind nodeKind() {
        return NodeKind.DECLARACION;
    }

    @Override
    public String toString() {
        return t.toString() + " " + id.toString();
    }
}
