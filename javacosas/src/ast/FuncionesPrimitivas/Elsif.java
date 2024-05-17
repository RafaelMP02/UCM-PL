package ast.FuncionesPrimitivas;

import ast.GeneracionCodigo.Comp;
import ast.NodoAST;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import ast.Expresiones.E;
import ast.Metaoperadores.Ambito;
import ast.Tipos.Booleano;
import ast.Tipos.NodoTipo;
import ast.Tipos.Tipado;
import ast.Vinculacion.Vinculacion;

public class Elsif implements NodoAST {
    private Elsif siguienteElsif = null;
    private Else siguienteElse  = null;

    //ambos o uno deben de ser nulos

    private Ambito ambito;
    private E cond;
    private int columna;
    private int fila;

    public Elsif(E cond, Ambito amb, Elsif siguienteElsif, Else siguienteElse, int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        this.cond = cond;
        this.ambito = amb;
        this.siguienteElsif = siguienteElsif;
        this.siguienteElse = siguienteElse;
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
        StringBuilder sb = new StringBuilder();
        sb.append("ELSIF(" + cond.toString() + ")" + ambito.toString());
        if (siguienteElsif != null) 
            sb.append(siguienteElsif.toString());
        if (siguienteElse != null)
            sb.append(siguienteElse.toString());

        return sb.toString();
    }

    @Override
    public void bind(Vinculacion vinc) {
        cond.bind(vinc);
        ambito.bind(vinc);

        //Elsif
        if (siguienteElsif != null) siguienteElsif.bind(vinc);
        
        //Else
        if (siguienteElse != null) siguienteElse.bind(vinc);

    }

    @Override
    public Set<NodoTipo> type(Set<NodoTipo> tiposEsperados) {
        cond.type(new LinkedHashSet<>(Arrays.asList(new Booleano())));
        Set<NodoTipo> tHijos = Tipado.enumToTipo(Tipado.TODOS);
        ambito.type(tHijos);
        
        //Elsif
        if (siguienteElsif != null) siguienteElsif.type(tHijos);        
        //Else
        if (siguienteElse != null) siguienteElse.type(tHijos);
        return null;
    }

    public String codeI(Comp hcon) {
        if(siguienteElsif != null) {
            return cond.codeE(hcon) + "if\n" + ambito.codeI(hcon) + "else\n" + siguienteElsif.codeI(hcon) + "end\n";
        } else if (siguienteElse != null) {
            return cond.codeE(hcon) + "if\n" + ambito.codeI(hcon) + "else\n" + siguienteElse.codeI(hcon) + "end\n" ;
        } else {
            return cond.codeE(hcon) + "if\n" + ambito.codeI(hcon) + "end\n";
        }

    }
}
