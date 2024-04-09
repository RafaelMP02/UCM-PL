package ast;

import ast.Expresiones.E;
import ast.Metaoperadores.Ambito;
import ast.Metaoperadores.CabecerAsig;

import java.util.LinkedList;

public class DefFuncion extends Definicion{
    CabecerAsig cabecera;

    LinkedList<E> parametros;

    Ambito ambito;

    public DefFuncion( CabecerAsig cabecera, LinkedList<E> parametros, Ambito ambito){
        this.cabecera = cabecera;
        this.parametros = parametros;
        this.ambito = ambito;
    }

    public String toString() {
        return cabecera.toString() + parametros.toString() +ambito.toString();
    }
}
