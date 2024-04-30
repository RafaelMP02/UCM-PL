package ast;


import ast.Vinculacion.Vinculacion;

public interface NodoAST {
    public static final String ERROR_STR = "ERROR";
    //public List<TiposEnum> type(); // for the future
    default void bind(Vinculacion vinc){} // Es interesante que devuelva un puntero en algunos casos
    // public ?? generateCode() // for the future
    public TiposDeNodos nodeKind();
    public String toString();
}
