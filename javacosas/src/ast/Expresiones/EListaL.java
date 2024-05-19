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
    public String codeE(Comp hcon) {
        /*String s = "";
        Struct ti = (Struct) this.tipo;
        Iterator<String> it = ti.getCampos().keySet().iterator();
        for(E ex: elementos) {
            String campo = it.next();
            s = s + ex.codeE(hcon);
            if(ex.kinE)
            s = s + "i32.store\n";
            s = s + "get_global $corh\n";
            s = s + "i32.const " + Integer.toString((hcon.buscaCampo(ti.toString(), campo))) + "\n";
            s = s + "i32.add";
            s = s + "tee_global $corh\n";

        }
        s = s + "set_global $corh\n";

        return s;*/
        return "";
    }
}
