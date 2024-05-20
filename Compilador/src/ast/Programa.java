package ast;

import ast.GeneracionCodigo.Comp;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

public interface Programa extends NodoAST{
    /* Interfaz para todas aquellas instrucciones que abran un ámbito */
    public String codeI(Comp hcom);

    public void recoleccionAtributos(LinkedHashMap<String,LinkedHashSet<Declaracion>> mapa_actual);

}
