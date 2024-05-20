package ast.FuncionesPrimitivas;


import ast.Expresiones.Identificador;
import ast.GeneracionCodigo.Comp;
import ast.Tipos.NodoTipo;
import ast.Tipos.TInstruccion;
import ast.Tipos.Tipado;
import ast.Tipos.Tipado.TiposEnum;
import ast.Vinculacion.Vinculacion;

import java.util.Set;

import ast.Instruccion;

public class Scans implements Instruccion {
    private Identificador id;
    private int columna;
    private int fila;

    public Scans(Identificador id, int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        this.id = id;
    }

    @Override
    public int getFila() {
        return this.fila;
    }

    @Override
    public int getColumna() {
        return this.columna;
    }

    public String toString() {
        return "SCANS(" + id.toString() + ")";
    }

    @Override
    public void bind(Vinculacion vinc) {
        id.bind(vinc);
    }

    @Override
    public Set<NodoTipo> type(Set<NodoTipo> tiposEsperados) {
        id.type(Tipado.enumToTipo(Tipado.TIPOS_ENTRADA_SALIDA));
        return Tipado.matchTipoEsperado(new TInstruccion(TiposEnum.OTRA_INSTRUCCION), tiposEsperados, fila, columna);
    }

    @Override
    public String codeI(Comp hco) {
        StringBuilder s = new StringBuilder();
        s.append(id.codeD(hco));
        s.append("(call $read)\n");
        s.append("(i32.store)\n");

        return s.toString();
    }
}
