package ast.Expresiones;

import ast.KindE;
import ast.Vinculacion.Vinculacion;

import java.util.LinkedList;

public class EListaC implements E{
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

    @Override
    public void bind(Vinculacion vinc) {
        for (E elemento : elementos) {
            elemento.bind(vinc);
        }
    }
}
