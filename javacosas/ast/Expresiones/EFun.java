package ast.Expresiones;

import ast.Instruccion;
import ast.LocatedNode;
import ast.Tipos.NodoTipo;
import ast.Tipos.Tipado;
import ast.Vinculacion.Vinculacion;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

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

    @Override
    public Set<NodoTipo> type() {
        List<Set<NodoTipo>> tParametros = new LinkedList<Set<NodoTipo>>();
        for (E parametro: parametros) {
            tParametros.add(parametro.type());
        }
        return Tipado.matchFuncion(funcion.type(), tParametros);
    }

    
}
