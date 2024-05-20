package ast.FuncionesPrimitivas;

import ast.Declaracion;
import ast.Expresiones.E;
import ast.GeneracionCodigo.Comp;
import ast.Metaoperadores.Ambito;
import ast.Tipos.Booleano;
import ast.Tipos.NodoTipo;
import ast.Tipos.TInstruccion;
import ast.Tipos.Tipado;
import ast.Tipos.Tipado.TiposEnum;
import ast.Vinculacion.Vinculacion;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;

import ast.NodoAST;
import ast.Programa;


public class While implements Programa {
    E cond;
    Ambito ambito;
    Programa programa;
    private int columna;
    private int fila;


    public While(E cond, Ambito amb, Programa p, int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        this.cond = cond;
        this.ambito = amb;
        this.programa = p;
    }

    public String toString() {
        if( cond != null) {
            return "WHILE(" + cond.toString() + ")" + ambito.toString() + programa.toString();
        } else {
            return "WHILE(" + NodoAST.ERROR_STR + ")" + ambito.toString() + programa.toString();
        }
    }

    @Override
    public void bind(Vinculacion vinc) {
        cond.bind(vinc);
        ambito.bind(vinc);
        programa.bind(vinc);
    }

    @Override
    public Set<NodoTipo> type(Set<NodoTipo> tiposEsperados) {
        cond.type(new LinkedHashSet<>(Set.of(new Booleano())));
        ambito.type(tiposEsperados);
        Set<NodoTipo> tipado = Tipado.matchTipoEsperado(new TInstruccion(TiposEnum.OTRA_INSTRUCCION), tiposEsperados, fila, columna);
        return Tipado.unirConjuntos(tipado, programa.type(tiposEsperados));
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
    public String codeI(Comp hcon) {
        StringBuilder s = new StringBuilder();
        return s.append("block \n loop\n").append(
                cond.codeE(hcon) ).append(
                        "i32.eqz \n" ).append(
                                "br_if 1\n" ).append(
                                        ambito.codeI(hcon) ).append(
                                                "br 0\n end\n end\n" ).append(
                                                        programa.codeI(hcon)).toString();
    }



    @Override
    public void recoleccionAtributos(LinkedHashMap<String,LinkedHashSet<Declaracion>> mapa_actual) {
        programa.recoleccionAtributos(mapa_actual);
    }

    public void getFun(Comp hcom){
        ambito.getFun(hcom);
    }
}
