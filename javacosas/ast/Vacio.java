package ast;

import java.util.Set;

import ast.Tipos.NodoTipo;

public class Vacio implements Programa{

    public Vacio(){}
    public TiposDeNodos nodeKind() {
        return TiposDeNodos.VACIO;
    }
    public String toString() {
        return "";
    }
    @Override
    public Set<NodoTipo> type() {
        return null;
    }
}
