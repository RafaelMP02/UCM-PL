package ast.Expresiones;

import ast.KindE;

import java.util.LinkedList;

public class EListaC extends E{
    private LinkedList<E> elementos;

    public EListaC(LinkedList<E> elementos) {
        this.elementos = elementos;
    }

    public KindE kind() {
        return KindE.ListaCorchetes;
    }

    public String toString() {
        return elementos.toString();
    }
}
