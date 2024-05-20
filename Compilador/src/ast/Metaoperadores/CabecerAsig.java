package ast.Metaoperadores;


import ast.GeneracionCodigo.Comp;
import ast.NodoAST;
import ast.Tipos.NodoTipo;


public interface CabecerAsig extends NodoAST {
    public boolean esAsignable();

    public NodoTipo tip();

    public default String codeD(Comp hcon){return "";}

    public boolean isDec();

}
