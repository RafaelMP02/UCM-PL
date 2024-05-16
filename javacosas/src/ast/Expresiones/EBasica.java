package ast.Expresiones;

import java.util.Set;

import ast.Tipos.NodoTipo;
import ast.Tipos.Tipado;

public abstract class EBasica extends E {
    protected String expString;

    public EBasica(int fila, int columna) {
        super(fila, columna);
        asignable = false;
    }

    public EBasica (){
        super();
        asignable = false;
    }

    @Override
    public Set<NodoTipo> type(Set<NodoTipo> tiposEsperados) {
        return Tipado.matchTipoEsperado(tipo, tiposEsperados, fila, columna);
    }

    @Override
    public String toString() {
        return this.expString;
    }

}
