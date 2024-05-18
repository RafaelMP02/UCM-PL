package ast;

import java.util.Set;

import ast.Expresiones.Identificador;
import ast.GeneracionCodigo.Comp;
import ast.Metaoperadores.Ambito;
import ast.Tipos.Clase;
import ast.Tipos.NodoTipo;
import ast.Tipos.TInstruccion;
import ast.Tipos.Tipado;
import ast.Tipos.Tipado.TiposEnum;
import ast.Vinculacion.Vinculacion;

public class DefClase implements Definicion{
    /* Define la clase, es decir, sus métodos y atributos. Pero no la instancia. 
    Los ámbitos de las claves son globales, así que  no apilan una nueva tabla de símbolos.*/
    Identificador nombre;
    Ambito ambito;
    boolean descendiendo;
    private Clase t;
    private int columna;
    private int fila;

    public DefClase(Identificador nombre, Ambito ambito, int fila, int columna) {
        this. fila = fila;
        this. columna = columna;
        this.nombre = nombre;
        this.ambito = ambito;
        t = new Clase(nombre, fila, columna);
    }

    public String toString() {
        return " DEFCLASE " + nombre.toString() + ambito.toString();
    }

    @Override
    public void bind(Vinculacion vinc) {
        ambito.bind(vinc);
        t.setCampos(ambito.getMapa()); //Guardan su tabla de símbolos al cerrarla en el nodo tipo
        vinc.insertarTipoNuevo(t, nombre.toString(), nombre.getFila(), nombre.getColumna());
    }

    @Override
    public Set<NodoTipo> type(Set<NodoTipo> tiposEsperados) {
        //Se espera que en el ámbito de la clase solo haya instruccioens válidas para éste
        Set<NodoTipo> tiposInstrucciones = Tipado.enumToTipo(Tipado.TIPOS_INSTR_CLASES);

        //Marcamos los tipos de los atributos
        t.setTiposAtributos(Tipado.matchDefTipoNuevo(ambito.type(tiposInstrucciones), nombre.toString(), nombre.getFila(), nombre.getColumna()));   

        //El tipo de devolución es una definición de clase
        return Set.of(new TInstruccion(TiposEnum.DEFCLASE));
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
    public String codeI(Comp hcom) {
        hcom.insertarTipoNuevo(t);
        return "";
    }
}
