package ast.FuncionesPrimitivas;


import ast.Expresiones.E;
import ast.Tipos.NodoTipo;
import ast.Tipos.Tipado;
import ast.Vinculacion.Vinculacion;

import java.util.Set;

import ast.Instruccion;
import ast.LocatedNode;
import ast.TiposDeNodos;

public class Return extends LocatedNode implements Instruccion {
    private E valor;

    public Return(E valor, int fila, int columna) {
        super(fila, columna);
        this.valor = valor;
    }

    public TiposDeNodos nodeKind() {
        return TiposDeNodos.RETURN;
    }

    public String toString(){
        return "RETURN " + valor.toString();
    }

    @Override
    public void bind(Vinculacion vinc) {
        valor.bind(vinc);
    }

    @Override
    public Set<NodoTipo> type() {
        Tipado.matchReturn(valor);
        return null; //FIXME hay que hacer bien los returns
    }
}
