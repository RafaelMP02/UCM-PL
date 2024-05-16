package ast;


import java.util.Set;

import ast.Tipos.NodoTipo;
import ast.Vinculacion.Vinculacion;

public interface NodoAST {
    public static final String ERROR_STR = "ERROR";
    /* Devuelve un conjunto de tipos factibles, es decir, el tipado de la expresión. Se le pasa como argumento el tipo esperado.*/
    public Set<NodoTipo> type(Set<NodoTipo> tiposEsperados); 
    default void bind(Vinculacion vinc){}
    // public ?? generateCode() // for the future
    public String toString();
    public int getFila();
    public int getColumna();

}
