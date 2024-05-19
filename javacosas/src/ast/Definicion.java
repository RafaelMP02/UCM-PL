package ast;

import ast.Tipos.TipoNuevo;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

public interface Definicion extends Instruccion {
    public TipoNuevo getTipo();

    public LinkedHashMap<String, LinkedHashSet<Declaracion>> getDecs();
}
