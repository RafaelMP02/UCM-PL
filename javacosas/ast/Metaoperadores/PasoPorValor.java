package ast.Metaoperadores;

import java.util.Set;

import ast.TiposDeNodos;
import ast.Tipos.Parametrico;
import ast.Tipos.NodoTipo;


public class PasoPorValor implements Parametrico {
    /* Operador en los argumentos de las funciones para que el paso de parámetro se realice por valor */
    private NodoTipo t;
    public PasoPorValor(NodoTipo t){
       this.t = t;
    }

    @Override
    public String toString() {
        return "$ " + t.toString();
    }

    public TiposDeNodos nodeKind() {
        return TiposDeNodos.VALOR;
    }

    @Override
    public Set<NodoTipo> type() {
        return t.type();
    }
}
