package ast.Expresiones;

import ast.GeneracionCodigo.Comp;
import ast.Tipos.NodoTipo;
import ast.Tipos.Struct;
import ast.Tipos.Tipado;
import ast.Vinculacion.Vinculacion;

import java.util.*;

public class EListaL extends E {
    /* Expresión lista de llaves. Sirve para inicializar un struct.*/
    private LinkedList<E> elementos;

    public EListaL(LinkedList<E> elementos, int fila, int columna) {
        super(fila, columna);
        this.elementos = elementos;
        asignable = false;
        exp = KindE.LISTALLAV;
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

        Set<NodoTipo> tipado = Tipado.matchListaL(tiposEsperados, elementos, fila, columna);
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
