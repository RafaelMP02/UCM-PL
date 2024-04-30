package ast.FuncionesPrimitivas;

import ast.Expresiones.E;
import ast.Vinculacion.Vinculacion;
import ast.Instruccion;
import ast.LocatedNode;
import ast.TiposDeNodos;

public class Prints extends LocatedNode implements Instruccion {
    private E valor;

    public Prints(E valor, int fila, int columna) {
        super(fila, columna);
        this.valor = valor;
    }

    public TiposDeNodos nodeKind() {
        return TiposDeNodos.PRINTS;
    }

    public String toString(){
        return "PRINTS(" + valor.toString() + ")";
    }

    @Override
    public void bind(Vinculacion vinc) {
        valor.bind(vinc);
    }
}
