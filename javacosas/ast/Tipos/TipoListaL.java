package ast.Tipos;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import ast.Expresiones.E;
import ast.Tipos.Tipado.TiposEnum;

public class TipoListaL implements NodoTipo {

    /* 
    Este tipo sirve para almacenar una lista de elementos entre llaves. Es el tipo de una expresión que inicializa los atributos de una clase o un struct. 
    No se puede instanciar una variable con este tipo. Se crean en el constructor de EListaL.
    */
    List<NodoTipo> tAtributos;

    public TipoListaL(LinkedList<E> atributos) {
        tAtributos = new ArrayList<NodoTipo>();

        for (E atributo : atributos) {
            Set<NodoTipo> tipos = atributo.type();
            tAtributos.add(Tipado.matchAtributo(tipos));
        }
    }

    @Override
    public TiposEnum typeToEnum() {
        return TiposEnum.LISTLAL;
    }

}
