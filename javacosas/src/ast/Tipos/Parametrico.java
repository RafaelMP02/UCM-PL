package ast.Tipos;

import ast.NodoAST;
import ast.Tipos.Tipado.TiposEnum;

public interface Parametrico extends NodoAST {
        public TiposEnum typeToEnum();
        public NodoTipo getTipo();

        public default boolean copia(){ return false;}
}
