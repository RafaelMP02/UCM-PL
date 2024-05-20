package ast.Operadores.BinOperadores;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import ast.Expresiones.KindE;
import ast.Tipos.Booleano;
import ast.Tipos.NodoTipo;

public class Distinto extends OperadorBin {
    public final static String OPSTRING = "Distinto";
    public Distinto(int fila, int columna) {
        super(fila, columna);
        exp = KindE.DISTINTO;
    }

    public String toString() {
        return Distinto.OPSTRING;
    }

    @Override
    public void inicializarTipado() {
        /* tComparable -> tComparable -> booleano */
        tipado = new ArrayList<Set<NodoTipo>>() {{
            add(new LinkedHashSet<>(Arrays.asList( new Booleano())));
            add(new LinkedHashSet<>(Arrays.asList( new Booleano())));
            add(new LinkedHashSet<>(Arrays.asList( new Booleano())));
        }};
    }
}
