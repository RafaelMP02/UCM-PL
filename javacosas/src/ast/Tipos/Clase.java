package ast.Tipos;

import java.util.List;
import java.util.Set;

import ast.Expresiones.Identificador;
import ast.Tipos.Tipado.TiposEnum;

public class Clase extends TipoNuevo{

    private int fila;
    private int columna;


    public Clase(Identificador iden, int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        this.iden = iden;
    }

    public Clase(List<Set<NodoTipo>> tiposAtributos) {
        this.iden = new Identificador(TipoNuevo.DEFAULT_ID);
        this.tiposAtributos = tiposAtributos;
    }

    public Clase () {
        this.iden = new Identificador(TipoNuevo.DEFAULT_ID);
    }

    @Override
    public TiposEnum typeToEnum() {
        return TiposEnum.CLASE;
    }


    public String toString() {
        return "CLASE " + iden.toString();
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
