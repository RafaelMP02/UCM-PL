package ast;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;

import ast.Expresiones.Identificador;
import ast.GeneracionCodigo.Comp;
import ast.Metaoperadores.Ambito;
import ast.Tipos.*;
import ast.Tipos.Tipado.TiposEnum;
import ast.Vinculacion.Vinculacion;

public class DefStruct implements Definicion  {
    /* Se definde el struct, es decir sus atributos. Pero no se instancia. 
    Los ámbitos de las claves son globales, así que  no apilan una nueva tabla de símbolos. */
    Identificador nombre;
    Ambito ambito;
    private Struct t;
    private int columna;
    private int fila;

    private LinkedHashMap<String,LinkedHashSet<Declaracion>> decs;

    public DefStruct(Identificador nombre, Ambito ambito, int fila, int columna) {
        this. fila = fila;
        this. columna = columna;
        this.nombre = nombre;
        this.ambito = ambito;
        this.decs = new LinkedHashMap<>();
        t = new Struct(nombre, fila, columna);
    }

    public String toString() {
        return " DEFSTRUCT " + nombre.toString() + ambito.toString();
    }

    @Override
    public void bind(Vinculacion vinc) {
        ambito.recoleccionAtributos(decs);
        vinc.abreAmbTNuevo(this);
        ambito.bind(vinc);
        t.setCampos(ambito.getMapa(), this); //Guardan su tabla de símbolos al cerrarla en el nodo tipo
        vinc.insertarTipoNuevo(t, nombre.toString(), nombre.getFila(), nombre.getColumna());
        vinc.cierraAmbTNuevo();
    }

    public LinkedHashMap<String, LinkedHashSet<Declaracion>> getDecs() {
        return decs;
    }

    @Override
    public Set<NodoTipo> type(Set<NodoTipo> tiposEsperados) {
        //Se espera que en el ámbito del struct solo haya instruccioens válidas para éste
        Set<NodoTipo> tiposInstrucciones = Tipado.enumToTipo(Tipado.TIPOS_INSTR_STRUCTS);

        //Marcamos los tipos de los atributos
        t.setTiposAtributos(Tipado.matchDefTipoNuevo(ambito.type(tiposInstrucciones), nombre.toString(), nombre.getFila(), nombre.getColumna()));

        //El tipo de devolución es una definición de struct
        return Collections.singleton(new TInstruccion(TiposEnum.DEFCLASE));
    }

    @Override
    public int getFila() {
        return this. fila;
    }

    @Override
    public int getColumna() {
        return this.columna;
    }



    @Override
    public TipoNuevo getTipo(){
        return this.t;
    }
    @Override
    public String codeI(Comp hcom) {
        hcom.insertarTipoNuevo(this);
        return ambito.codeFunc(hcom);
    }

    @Override
    public String codeFunc(Comp hcon){
        return this.codeI(hcon);
    }
}
