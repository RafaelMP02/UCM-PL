package ast.Tipos;

import java.util.List;
import java.util.Set;

import ast.Expresiones.Identificador;
import ast.Tipos.Tipado.TiposEnum;

public class Struct extends TipoNuevo{
    

    private int columna;
    private int fila;

    public Struct(Identificador iden, int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        this.iden = iden;
    }

    public Struct () {
        this.fila = -1;
        this.columna = -1;
        this.iden = new Identificador(TipoNuevo.DEFAULT_ID);
    }

    public Struct(List<Set<NodoTipo>> tiposAtributos) {
        this.tiposAtributos = tiposAtributos;
        this.iden = new Identificador(TipoNuevo.DEFAULT_ID);
    }

    @Override
    public TiposEnum typeToEnum() {
        return TiposEnum.STRUCT;
    }

    public String toString() {
        return "STRUCT " + iden.toString();
    }

    @Override
    public int getFila() {
        return this.fila;
    }

    @Override
    public int getColumna() {
        return this.columna;
    }

}
