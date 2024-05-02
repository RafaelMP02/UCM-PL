package ast.Expresiones;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ast.LocatedNode;
import ast.Tipos.Booleano;
import ast.Tipos.NodoTipo;

public class True extends LocatedNode implements E{
    public True(int fila, int columna) {
        super(fila, columna);
    }

    public String toString() {
        return "TRUE";
    }

    @Override
    public Set<NodoTipo> type() {
        return new HashSet<NodoTipo>(Collections.singleton(new Booleano()));
    }

    
}

