package ast;

import ast.Expresiones.Identificador;
import ast.Metaoperadores.CabecerAsig;
import ast.Tipos.Tipo;
import ast.Vinculacion.Vinculacion;

public class Declaracion implements Instruccion, CabecerAsig  {
    /* Declara una variable de cualquier tipo (incluso funcional) */
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

    @Override
    public void bind(Vinculacion vinc) {
        vinc.insertaId(id.toString(), this);
    }
}
