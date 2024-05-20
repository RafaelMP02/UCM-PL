package ast.Operadores.BinOperadores;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import ast.Expresiones.KindE;
import ast.Tipos.Booleano;
import ast.Tipos.NodoTipo;
import ast.Tipos.Tipado;

public class MenorIgual extends OperadorBin {
    public final static String OPSTRING = "MenorIgual";
    public MenorIgual(int fila, int columna) {
        super(fila, columna);
        exp = KindE.MENIGUAL;
    }

    public String toString() {
        return MenorIgual.OPSTRING;
    }

    @Override
    public void inicializarTipado() {
        /* tComparable -> tComparable -> booleano */
        tipado = new ArrayList<Set<NodoTipo>>() {{
            add(Tipado.enumToTipo(Tipado.TIPOS_COMPARABLES));
            add(Tipado.enumToTipo(Tipado.TIPOS_COMPARABLES));
            add(new LinkedHashSet<>(Arrays.asList( new Booleano())));
        }};
    }
}
