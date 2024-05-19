package ast;

import ast.GeneracionCodigo.Comp;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

public interface Instruccion extends NodoAST {
    /* Interfaz para todas las instrucciones que terminene en ";" y no abran ámbito */
    public  String codeI(Comp hcom);

    public default String codeFunc(Comp hcom) {return "";};

    public default void recoleccionAtributos(LinkedHashMap<String,LinkedHashSet<Declaracion>> mapa_actual){
    }

}
