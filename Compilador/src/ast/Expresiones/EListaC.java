package ast.Expresiones;

import ast.GeneracionCodigo.Comp;
import ast.Tipos.NodoTipo;
import ast.Tipos.Tipado;
import ast.Vinculacion.Vinculacion;

import java.util.LinkedList;
import java.util.Set;

public class EListaC extends E {
    /* Expresión de lista corchetes para la asignación de arrays. */
    private LinkedList<E> elementos;

    public EListaC(LinkedList<E> elementos, int fila, int columna) {
        super(fila, columna);
        this.elementos = elementos;
        asignable = false;
        this.exp = KindE.LISTACOR;
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
    public Set<NodoTipo> type(Set<NodoTipo> tiposEsperados) {
        Set<NodoTipo> tipado = Tipado.matchListaC(tiposEsperados, elementos, fila, columna);
        this.tipo = tipado.iterator().next();
        return tipado;
    }

    @Override
    public String codeB(Comp hcon){
        StringBuilder s = new StringBuilder();
        for(E e: elementos){
            s.append(e.codeB(hcon));
        }

        return s.toString();
    }
}
