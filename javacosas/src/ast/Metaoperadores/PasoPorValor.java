package ast.Metaoperadores;

import java.util.Set;

import ast.Tipos.Parametrico;
import ast.Tipos.Tipado.TiposEnum;
import ast.Tipos.NodoTipo;


public class PasoPorValor implements Parametrico {
    /* Operador en los argumentos de las funciones para que el paso de parámetro se realice por valor */
    private NodoTipo t;
    private int columna;
    private int fila;
    public PasoPorValor(NodoTipo t, int fila, int columna){
        this.fila = fila;
        this.columna = columna;
       this.t = t;
    }

    

    @Override
    public String toString() {
        return "$ " + t.toString();
    }


    @Override
    public NodoTipo getTipo() {
        return this.t;
    }

    @Override
    public Set<NodoTipo> type(Set<NodoTipo> tipoEsperado) { //TODO implementar esto bien
        return t.type(tipoEsperado);
    }

    public NodoTipo geTipo() {
        return this.t;
    }

    @Override
    public TiposEnum typeToEnum() {
        return t.typeToEnum();
    }



    @Override
    public int getFila() {
        return fila;
    }



    @Override
    public int getColumna() {
        return columna;
    }

    @Override
    public boolean copia(){return true;}
}
