package ast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ast.Expresiones.Identificador;
import ast.Metaoperadores.CabecerAsig;
import ast.Tipos.Funcional;
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
        return " " + t.toString() + " " + id.toString();
    }

    @Override
    public void bind(Vinculacion vinc) {
        vinc.insertaId(id.toString(), this, id.getFila(), id.getColumna());
    }

    @Override
    public Set<NodoTipo> type() {
        return Collections.singleton(t);
    }

    public NodoTipo getTipo() {
        return t;
    }
}
