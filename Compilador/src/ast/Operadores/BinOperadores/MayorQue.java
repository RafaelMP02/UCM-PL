package ast.Operadores.BinOperadores;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import ast.Expresiones.KindE;
import ast.Tipos.Booleano;
import ast.Tipos.NodoTipo;
import ast.Tipos.Tipado;

public class MayorQue extends OperadorBin {
    public final static String OPSTRING = "MayorQue";
    public MayorQue(int fila, int columna) {
        super(fila, columna);
        exp = KindE.MAYQ;
    }

    public String toString() {
        return MayorQue.OPSTRING;
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
