package ast.Metaoperadores;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import ast.Declaracion;
import ast.GeneracionCodigo.Comp;
import ast.NodoAST;
import ast.Tipos.NodoTipo;
import ast.Tipos.Tipado;
import ast.Programa;
import ast.Vinculacion.Vinculacion;

public class Ambito implements NodoAST {
    private Programa programa;
    private Map<String,LinkedHashSet<Declaracion>> mapa;
    private int columna;
    private int fila;

    public Ambito(Programa prog, int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        this.programa = prog;
    }

    public String toString() {
        return " {" + programa.toString() + "}";
    }

    @Override
    public void bind(Vinculacion vinc) {
        vinc.abreBloque();
        programa.bindFunc(vinc);
        programa.bind(vinc);
        mapa = vinc.cierraBloque();
    }

    @Override
    public Set<NodoTipo> type(Set<NodoTipo> tiposEsperados) {
        /* Devuelve el tipado de todas las instrucciones del programa. */
        Set<NodoTipo> tPrograma = programa.type(tiposEsperados);
        Tipado.matchAmbito(mapa, tPrograma); //Comprueba que no hay ambigüedad en las funciones
        return  tPrograma;
    }

    public Map<String,LinkedHashSet<Declaracion>> getMapa() {
        return mapa;
    }

    @Override
    public int getFila() {
        return this.fila;
    }

    @Override
    public int getColumna() {
        return this.columna;
    }

    public void recoleccionAtributos(LinkedHashMap<String,LinkedHashSet<Declaracion>> mapa_actual){
        programa.recoleccionAtributos(mapa_actual);
    }
    @Override
    public String codeI(Comp hcom){
        StringBuilder s = new StringBuilder();
        hcom.abreBloque();
        //s.append("global.get $SP\n");
        //s.append("global.set $MP\n");
        s.append(programa.codeI(hcom));
        hcom.cierraBloque();
        //s.append("global.get $MP\n");
        //s.append("global.set $SP\n");
        //s.append("global.get $SP\n");
        //s.append("i32.load\n");
        //s.append("global.set $MP\n");
        return s.toString();
    }
   @Override
    public String codeFunc(Comp hcon){
        hcon.abreBloque();
        String s = programa.codeFunc(hcon);
        hcon.cierraBloque();
        return s;
    }



    public void getFun(Comp hcom){
        programa.getFun(hcom);
    }

    public Programa getPrograma() {
        return programa;
    }
}
