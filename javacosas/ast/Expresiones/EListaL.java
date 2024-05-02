package ast.Expresiones;

import ast.Tipos.NodoTipo;
import ast.Tipos.TipoListaL;
import ast.Vinculacion.Vinculacion;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Set;

public class EListaL implements E{
    /* Expresión lista de llaves. Sirve para inicializar un struct.*/
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

    @Override
    public Set<NodoTipo> type() {
        return Collections.singleton(new TipoListaL(elementos));
    }

    

}
