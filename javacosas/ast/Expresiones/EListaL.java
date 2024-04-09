package ast.Expresiones;

import ast.KindE;

import java.util.LinkedList;

public class EListaL extends E{
    private LinkedList<E> elementos;

    public EListaL(LinkedList<E> elementos) {
        this.elementos = elementos;
    }

    public KindE kind() {
        return KindE.ListaLLaves;
    }

    public String toString() {
        return elementos.toString();
    }
}
