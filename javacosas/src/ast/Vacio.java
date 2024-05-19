package ast;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;

import ast.GeneracionCodigo.Comp;
import ast.Tipos.NodoTipo;

public class Vacio implements Programa{

    public Vacio(){}
    
    public String toString() {
        return "";
    }
    @Override
    public Set<NodoTipo> type(Set<NodoTipo> tiposEsperados) {
        return new HashSet<>();
    }

    @Override
    public int getFila() {
        return -1;
    }

    @Override
    public int getColumna() {
        return -1;
    }

    @Override
    public String codeI(Comp hcon) {
        return "";
    }

    @Override
    public void recoleccionAtributos(LinkedHashMap<String,LinkedHashSet<Declaracion>> mapa_actual) {
    }
}
