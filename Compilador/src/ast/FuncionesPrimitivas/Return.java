package ast.FuncionesPrimitivas;


import ast.Expresiones.E;
import ast.GeneracionCodigo.Comp;
import ast.Tipos.NodoTipo;
import ast.Tipos.TInstruccion;
import ast.Tipos.Tipado;
import ast.Tipos.Tipado.TiposEnum;
import ast.Vinculacion.Vinculacion;

import java.util.Set;

import ast.Instruccion;


public class Return  implements Instruccion {
    private E valor;
    private int columna;
    private int fila;

    public Return(E valor, int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        this.valor = valor;
    }

    @Override
    public int getFila() {
        return this.fila;
    }

    @Override
    public int getColumna() {
        return this.columna;
    }

    public String toString(){
        return " RETURN " + valor.toString();
    }

    @Override
    public void bind(Vinculacion vinc) {
        if (valor != null)
            valor.bind(vinc);
    }

    @Override
    public Set<NodoTipo> type(Set<NodoTipo> tiposEsperados) {
        return Tipado.matchReturn(valor, tiposEsperados, fila, columna);
    }

    @Override
    public String codeI(Comp hco) {
        return valor.codeE(hco) + "(return)\n";
    }
}
