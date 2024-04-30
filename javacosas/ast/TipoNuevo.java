package ast;

import java.util.HashMap;

public interface TipoNuevo {
    /* Interfaz para aquellos nodos que crean tipos (clases y structs) */
    public HashMap<String, NodoAST> getCampos();
}
