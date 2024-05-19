package ast.FuncionesPrimitivas;

import ast.GeneracionCodigo.Comp;
import ast.NodoAST;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import ast.Expresiones.Num;
import ast.Metaoperadores.Ambito;
import ast.Tipos.Entero;
import ast.Tipos.NodoTipo;
import ast.Tipos.Tipado;
import ast.Vinculacion.Vinculacion;


public class Case implements NodoAST {
    private Num cond;
    private Case caso;
    private Ambito ambito;
    private int columna;
    private int fila;

    public Case(Num cond, Ambito amb, Case caso, int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        this.cond = cond;
        this.ambito= amb;
        this.caso = caso;
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
        if(caso != null) {
            return "CASE " + cond.toString() + ambito.toString() + caso.toString();
        } else {
            return "CASE " + cond.toString() + ambito.toString();
        }
    }

    @Override
    public void bind(Vinculacion vinc) {
        ambito.bind(vinc);
        if (caso != null)
            caso.bind(vinc);
    }

    @Override
    public Set<NodoTipo> type(Set<NodoTipo> tiposEsperados) {
        cond.type(new LinkedHashSet<>(Arrays.asList(new Entero())));

        Set<NodoTipo> tHijos = Tipado.enumToTipo(Tipado.TIPOS_INSTR);
        ambito.type(tHijos);
        if (caso != null)
            caso.type(tHijos);
        
        return null;
    }

    public String codeI(Comp hcon){
        StringBuilder s = new StringBuilder();
        if(caso != null){
            s.append("get_global $tem\n" ).append( "i32.const " ).append( cond.toString() ).append( "\n i32.eq\n if\n" ).append( ambito.codeI(hcon) ).append( "else\n"  ).append( caso.codeI(hcon) ).append( "end\n");
        } else {
            s.append( "get_global $tem\n" ).append( "i32.const " ).append( cond.toString() ).append( "\n i32.eq\n if\n" ).append( ambito.codeI(hcon) ).append( "else\n" ).append( hcon.getha() ).append( "end\n");
        }

        return s.toString();
    }

    @Override
    public String codeFunc(Comp hcon){
        return ambito.codeFunc(hcon);
    }
}
