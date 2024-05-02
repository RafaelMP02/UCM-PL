package ast.Expresiones;

import ast.Tipos.NodoTipo;
import ast.Tipos.TipoListaC;
import ast.Vinculacion.Vinculacion;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Set;

public class EListaC implements E{
    private LinkedList<E> elementos;

    public EListaC(LinkedList<E> elementos) {
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

    @Override
    public Set<NodoTipo> type() {
        return Collections.singleton(new TipoListaC(elementos));
    }
}
