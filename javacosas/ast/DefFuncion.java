package ast;

import ast.Expresiones.E;
import ast.Metaoperadores.Ambito;
import ast.Metaoperadores.CabecerAsig;
import ast.Vinculacion.Vinculacion;

import java.util.LinkedList;

public class DefFuncion extends Definicion{
    /* Define la función, es decir, la "instancia" porque se vincula el identificador a este nodo.*/
    CabecerAsig cabecera;

    LinkedList<E> argumentos;

    Ambito ambito;

    public DefFuncion( CabecerAsig cabecera, LinkedList<E> arg, Ambito ambito){
        this.cabecera = cabecera;
        this.argumentos = arg;
        this.ambito = ambito;
    }

    public String toString() {
        return cabecera.toString() + argumentos.toString() +ambito.toString();
    }

    @Override
    public void bind(Vinculacion vinc) {
        // TODO deffuncion?????????????
    }
}
