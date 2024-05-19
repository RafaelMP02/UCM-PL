package ast.FuncionesPrimitivas;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;

import ast.Declaracion;
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
        this.inicio = inicio;
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
        StringBuilder str = new StringBuilder();
        str.append("FOR (");
        if (inicio != null)
            str.append(inicio.toString() + " ; ");
        else    
            str.append("ERROR ; ");

        str.append(fin.toString() + " ; ");

        if (paso != null)
            str.append(paso.toString() + ")");
        else    
            str.append("ERROR)");
        str.append(ambito.toString() + programa.toString());
        return str.toString();
    }
    @Override
    public void bind(Vinculacion vinc) {
        if (inicio != null)
            inicio.bind(vinc);
        fin.bind(vinc);
        if (paso != null)
            paso.bind(vinc);
        ambito.bind(vinc);
        programa.bind(vinc);
    }
    @Override
    public Set<NodoTipo> type(Set<NodoTipo> tiposEsperados) {
        if (inicio != null)
            inicio.type(Tipado.enumToTipo(Set.of(TiposEnum.ASIGNACION, TiposEnum.DECVARIABLE)));
        fin.type(new LinkedHashSet<>(Arrays.asList(new Booleano())));
        if (paso != null)
            paso.type(Tipado.enumToTipo(Set.of(TiposEnum.ASIGNACION)));
        ambito.type(Tipado.enumToTipo(Tipado.TIPOS_INSTR));
        
        Set<NodoTipo> tipado = Tipado.matchTipoEsperado(new TInstruccion(TiposEnum.OTRA_INSTRUCCION), tiposEsperados, fila, columna);

        tipado.addAll(programa.type(tiposEsperados));   

        return tipado;
    }

    @Override
    public String codeI(Comp hcon) {
       StringBuilder s = new StringBuilder();
        s.append(
                inicio.codeI(hcon) ).append(
                "block \n loop\n" ).append(
                        fin.codeE(hcon) ).append(
                                "i32.eqz \n" ).append(
                                        "br_if 1\n" ).append(
                                                ambito.codeI(hcon) ).append(
                                                        paso.codeI(hcon) ).append(
                                                                "br 0\n end\n" ).append(
                                                                        programa.codeI(hcon));
        return s.toString();
    }

    @Override
    public String codeFunc(Comp hcon){
        return ambito.codeFunc(hcon);
    }

    @Override
    public void recoleccionAtributos(LinkedHashMap<String,LinkedHashSet<Declaracion>> mapa_actual) {
         programa.recoleccionAtributos(mapa_actual);
    }


}
