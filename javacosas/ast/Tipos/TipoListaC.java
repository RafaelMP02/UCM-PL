package ast.Tipos;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import ast.Expresiones.E;
import ast.Tipos.Tipado.TiposEnum;

public class TipoListaC implements NodoTipo {

    /* 
    Este tipo sirve para almacenar una lista de elementos entre corchetes. Es el tipo de una expresión que inicializa un array. 
    No se puede instanciar una variable con este tipo. Se crean en el constructor de EListaC.
    */
    List<NodoTipo> tElementos;

    public TipoListaC(LinkedList<E> elementos) {
        tElementos = new ArrayList<NodoTipo>();

        for (E elemento : elementos) {
            Set<NodoTipo> tipos = elemento.type();
            tElementos.add(Tipado.matchElemArray(tipos));
        }
    }

    @Override
    public TiposEnum typeToEnum() {
        return TiposEnum.LISTAC;
    }

}
