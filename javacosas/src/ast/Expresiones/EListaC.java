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
    public String codeE(Comp hcon) {
        String s = "";
        int tam = hcon.getTam();
        for(E ex: elementos) {
            s = s + ex.codeE(hcon);
            s = s + "i32.store\n";
            s = s + "get_global $corh\n";
            s = s + "i32.const " + Integer.toString(tam) + "\n";
            s = s + "i32.add";
            s = s + "tee_global $corh\n";

        }
        s = s + "set_global $corh\n";

        return s; 
    }
}
