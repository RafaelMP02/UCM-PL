package ast.FuncionesPrimitivas;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import ast.GeneracionCodigo.Comp;
import ast.Programa;
import ast.Expresiones.E;
import ast.Metaoperadores.Ambito;
import ast.Metaoperadores.Asignacion;
import ast.Tipos.Booleano;
import ast.Tipos.NodoTipo;
import ast.Tipos.TInstruccion;
import ast.Tipos.Tipado;
import ast.Tipos.Tipado.TiposEnum;
import ast.Vinculacion.Vinculacion;

public class For implements Programa {
    private Asignacion inicio;
    private Asignacion paso;
    private E fin;
    private Ambito ambito;
    private Programa programa;
    private int columna;
    private int fila;

   public For(Asignacion inicio, Asignacion paso, E fin, Ambito amb, Programa p, int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        this.paso = paso;
        this.fin = fin;
        this.ambito = amb;
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
        return "FOR(" + inicio.toString() +";" + fin.toString() + ";" + paso.toString() + ")" + ambito.toString() + programa.toString();
    }
    @Override
    public void bind(Vinculacion vinc) {
        ambito.bind(vinc);
        programa.bind(vinc);
    }
    @Override
    public Set<NodoTipo> type(Set<NodoTipo> tiposEsperados) {
        fin.type(new LinkedHashSet<>(Arrays.asList(new Booleano())));
        ambito.type(Tipado.enumToTipo(Tipado.TODOS));
        
        Set<NodoTipo> tipado = Tipado.matchTipoEsperado(new TInstruccion(TiposEnum.OTRA_INSTRUCCION), tiposEsperados, fila, columna);

        tipado.addAll(programa.type(tiposEsperados));   

        return tipado;
    }

    @Override
    public String codeI(Comp hcon) {
        return inicio.codeI(hcon) + "block \n loop\n" + fin.codeE(hcon) + "i32.eqz \n" + "br_if 1\n" + ambito.codeI(hcon) + paso.codeI(hcon) + "br 0\n end\n" + programa.codeI(hcon);
    }


}
