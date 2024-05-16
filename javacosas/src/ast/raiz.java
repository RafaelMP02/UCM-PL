package ast;

import ast.GeneracionCodigo.Comp;
import ast.Tipos.NodoTipo;
import ast.Tipos.Tipado;
import ast.Vinculacion.Vinculacion;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class raiz implements NodoAST{
    Programa programa;

    private Map<String, LinkedHashSet<Declaracion>> mapa;

    public  raiz(Programa programa){
        this.programa = programa;
    }

    @Override
    public Set<NodoTipo> type(Set<NodoTipo> tiposEsperados) {
        /* Devuelve el tipado de todas las instrucciones del programa. */
        Set<NodoTipo> tPrograma = programa.type(tiposEsperados);
        Tipado.matchAmbito(mapa, tPrograma); //Comprueba que no hay ambigüedad en las funciones
        return  tPrograma;
    }


    public Map<String, LinkedHashSet<Declaracion>> getMapa(){
        return mapa;
    }

    @Override
    public int getFila() {
        return programa.getFila();
    }

    @Override
    public int getColumna() {
        return programa.getColumna();
    }

    @Override
    public void bind(Vinculacion vinc) {
        vinc.abreBloque();
        programa.bind(vinc);
        mapa = vinc.cierraBloque();
    }

    public void compile(BufferedWriter output){
        try {
            output.write(programa.codeI(new Comp()));
            output.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public String toString(){return  programa.toString();}
}
