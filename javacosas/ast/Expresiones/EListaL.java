package ast.Expresiones;

import ast.Vinculacion.Vinculacion;

import java.util.LinkedList;

public class EListaL implements E{
    private LinkedList<E> elementos;

    public EListaL(LinkedList<E> elementos) {
        this.elementos = elementos;
    }

    public String toString() {
        return elementos.toString();
    }

    @Override
    public void bind(Vinculacion vinc) {
        for (E elemento : elementos) {
            elemento.bind(vinc);
        }
    }
}
