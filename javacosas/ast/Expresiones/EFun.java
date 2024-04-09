package ast.Expresiones;

import ast.KindE;

import java.util.LinkedList;

public class EFun extends E{
    private E funcion;
    private LinkedList<E> parametros;

    public EFun(E funcion, LinkedList<E> parametros){
        this.funcion = funcion;
        this.parametros = parametros;
    }

    public KindE kind() {
        return KindE.AccesoFuncion;
    }

    public String toString() {
       return funcion.toString() + parametros.toString();
    }
}
