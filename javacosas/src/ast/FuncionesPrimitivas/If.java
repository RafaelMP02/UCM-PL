package ast.FuncionesPrimitivas;

import ast.Expresiones.E;
import ast.GeneracionCodigo.Comp;
import ast.Metaoperadores.Ambito;
import ast.Tipos.Booleano;
import ast.Tipos.NodoTipo;
import ast.Tipos.TInstruccion;
import ast.Tipos.Tipado;
import ast.Tipos.Tipado.TiposEnum;
import ast.Vinculacion.Vinculacion;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import ast.Programa;

public class If implements Programa {
    //ambos o uno deben de ser nulos
    private Elsif siguienteElsif = null;
    private Else siguienteElse  = null;

    
    private Ambito ambito;
    private E cond;
    private Programa programa;
    private int columna;
    private int fila;

    public If(E cond, Ambito Amb, Programa p,Elsif siguienteElsif, Else siguienteElse, int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        this.cond = cond;
        this.ambito = Amb;
        this.siguienteElsif = siguienteElsif;
        this.siguienteElse = siguienteElse;
        this.programa = p;

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
        sb.append("IF(" + cond.toString() + ")" + ambito.toString());
        if (siguienteElsif != null) 
            sb.append(siguienteElsif.toString());
        if (siguienteElse != null)
            sb.append(siguienteElse.toString());

        sb.append(programa.toString());
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

        programa.bind(vinc);

    }

    @Override
    public Set<NodoTipo> type(Set<NodoTipo> tiposEsperados) {
        cond.type(new LinkedHashSet<>(Arrays.asList(new Booleano())));
        Set<NodoTipo> tipadoAmbitos = Tipado.enumToTipo(Tipado.TODOS);
        ambito.type(tipadoAmbitos);
        
        //Elsif
        if (siguienteElsif != null) siguienteElsif.type(tipadoAmbitos);
        
        //Else
        if (siguienteElse != null) siguienteElse.type(tipadoAmbitos);

        Set<NodoTipo> tipado = Tipado.matchTipoEsperado(new TInstruccion(TiposEnum.OTRA_INSTRUCCION), tiposEsperados, fila, columna);

        tipado.addAll(programa.type(tiposEsperados));   

        return tipado;
    }

    @Override
    public String codeI(Comp hcon) {
        if(siguienteElsif != null) {
            return cond.codeE(hcon) + "if\n" + ambito.codeI(hcon) + "else\n" + siguienteElsif.codeI(hcon) + "end\n" + programa.codeI(hcon);
        } else if (siguienteElse != null) {
            return cond.codeE(hcon) + "if\n" + ambito.codeI(hcon) + "else\n" + siguienteElse.codeI(hcon) + "end\n" + programa.codeI(hcon);
        } else {
            return cond.codeE(hcon) + "if\n" + ambito.codeI(hcon) + "end\n" + programa.codeI(hcon);
        }

    }
}
