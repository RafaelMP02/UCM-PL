package ast.Operadores.BinOperadores;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import ast.Expresiones.KindE;
import ast.Tipos.Clase;
import ast.Tipos.Funcional;
import ast.Tipos.NodoTipo;
import ast.Tipos.Struct;
import ast.Tipos.Tipado;

public class AccesoCampo extends OperadorBin {
    public final static String OPSTRING = "AccesoCampo";
    public AccesoCampo(int fila, int columna) {
        super(fila, columna);
        exp = KindE.ACCCAMPO;
    }
    public String toString() {
        return AccesoCampo.OPSTRING;
    }
    @Override
    public void inicializarTipado() {
        /* 
        struct -> tAtributo 
        class -> tAtributo
        class -> tFuncional
        */

        tipado = new ArrayList<Set<NodoTipo>>() {{
            add(new LinkedHashSet<>(Arrays.asList (new Struct(), new Clase())));
            Set<NodoTipo> tipos = Tipado.enumToTipo(Tipado.TIPOS_ATRIBUTOS);
            tipos.add(new Funcional());
            add(tipos);
        }};
    }
}
