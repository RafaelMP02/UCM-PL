package ast;

import ast.Tipos.TipoNuevo;

public interface Definicion extends Instruccion {
    public TipoNuevo getTipo();
}
