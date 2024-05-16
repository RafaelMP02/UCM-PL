package ast.Tipos;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;


public interface NodoTipo extends Parametrico {
    /* Nodo del AST de un tipo */
    @Override
    public default Set<NodoTipo> type(Set<NodoTipo> tiposEsperados) {
        
        return new LinkedHashSet<>(Arrays.asList(this));
    }
    public default boolean admiteSobrecarga() { return false;}

    public default NodoTipo getTipo() { return this; }

    public default int getTam(){ return 0;}
}
