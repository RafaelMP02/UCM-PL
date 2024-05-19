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

public class Switch implements Programa {
    private E cond;
    private Case caso;

    private Ambito ambito;

    private Programa programa;
    private int columna;
    private int fila;

    public Switch(E cond, Ambito amb, Programa p, Case caso, int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        this.cond = cond;
        this.ambito = amb;
        this.programa = p;
        this.caso = caso;
    }


    public String toString() {
        if (caso != null) {
            return "SWITCH(" + cond.toString() + "){" + caso.toString() + "DEFAULT" + ambito.toString() + "}" + programa.toString();
        } else {
            return "SWITCH(" + cond.toString() + "){" + "DEFAULT" + ambito.toString() + "}" + programa.toString();
        }
    }

    @Override
    public void bind(Vinculacion vinc) {
        cond.bind(vinc);
        if (caso != null)
            caso.bind(vinc);    //Ámbitos de los posibles "case"
        ambito.bind(vinc);  //Ámbito del "default"
        programa.bind(vinc);
    }

    @Override
    public Set<NodoTipo> type(Set<NodoTipo> tiposEsperados) {
        cond.type(new LinkedHashSet<>(Arrays.asList(new Booleano())));

        Set<NodoTipo> tHijos = Tipado.enumToTipo(Tipado.TIPOS_INSTR);
        ambito.type(tHijos);
        if (caso != null)
            caso.type(tHijos);

        Set<NodoTipo> tipado = Tipado.matchTipoEsperado(new TInstruccion(TiposEnum.OTRA_INSTRUCCION), tiposEsperados, fila, columna);

        tipado.addAll(programa.type(tiposEsperados));   

        return tipado;
    }


    @Override
    public int getFila() {
        return this.fila;
    }

    @Override
    public int getColumna() {
        return this.columna;
    }

    @Override
    public String codeI(Comp hco) {
        hco.setS(ambito.codeI(hco));
        StringBuilder s = new StringBuilder();
        return s.append(cond.codeE(hco)).append(
                "set_golbal $temp\n" ).append(
                        caso.codeI(hco) ).append(
                                programa.codeI(hco)).toString();
    }

    @Override
    public String codeFunc(Comp hcon){
        StringBuilder s = new StringBuilder();
        if(caso != null){
            s.append(caso.codeFunc(hcon));
        }
        return s.append(ambito.codeFunc(hcon)).append( programa.codeFunc(hcon)).toString();
    }
}
