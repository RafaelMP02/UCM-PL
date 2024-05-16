package ast.Metaoperadores;

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

    public String codeI(Comp hcom){
        hcom.abreBloque();
        String s = programa.codeI(hcom);
        hcom.cierraBloque();
        return s;
    }

    public int numFun(){
        int count = 0;
        for(String s: mapa.keySet()) {
            for(Declaracion X: mapa.get(s)){
                if(X.getTipo().typeToEnum() == Tipado.TiposEnum.FUNCIONAL){
                    count = count + 1;
                }
            }
        }
        return count;
    }
}
