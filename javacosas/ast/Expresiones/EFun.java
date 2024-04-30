package ast.Expresiones;

import ast.Instruccion;
import ast.LocatedNode;
import ast.Vinculacion.Vinculacion;

import java.util.LinkedList;

public class EFun extends LocatedNode implements Instruccion, E {
    private E funcion;
    private LinkedList<E> parametros;

    public EFun(E funcion, LinkedList<E> parametros, int fila, int columna) {
        super(fila, columna);
        this.funcion = funcion;
        this.parametros = parametros;
    }

    public String toString() {
       return funcion.toString() + parametros.toString();
    }

    @Override
    public void bind(Vinculacion vinc) {
        funcion.bind(vinc);
        for (E parametro : parametros) {
            parametro.bind(vinc);
        }
    }
}
