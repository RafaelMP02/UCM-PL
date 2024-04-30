package ast;

import ast.Expresiones.Identificador;
import ast.Metaoperadores.CabecerAsig;
import ast.Tipos.NodoTipo;
import ast.Vinculacion.Vinculacion;

public class Declaracion implements Instruccion, CabecerAsig  {
    /* Declara una variable de cualquier tipo (incluso funcional) */
    private NodoTipo t;

    private Identificador id;

    public Declaracion(NodoTipo t, Identificador id){
        this.t = t;
        this.id = id;
    }

    public TiposDeNodos nodeKind() {
        return TiposDeNodos.DECLARACION;
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
