package ast;


import java.util.Set;

import ast.Tipos.NodoTipo;
import ast.Vinculacion.Vinculacion;

public interface NodoAST {
    public static final String ERROR_STR = "ERROR";
    /* El tipado devuelve una lista de listas de conjuntos de tipos. La razón de la primera lista es por la sobrecarga de funciones (pueden devolver varios 
    tipados distintos). La segunda lista se debe a que los tipos funcionales no tendrán un solo tipo, sino que tendrán un tipo por cada parámetro y un tipo de
    devolución. */
    public Set<NodoTipo> type(); 
    default void bind(Vinculacion vinc){}
    // public ?? generateCode() // for the future
    public TiposDeNodos nodeKind();
    public String toString();

}
