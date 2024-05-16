package ast.Operadores.UnOperador;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import ast.Expresiones.KindE;
import ast.Tipos.Entero;
import ast.Tipos.NodoTipo;

public class MenosMenos extends OperadorUn{
    /* Operador decremento */
    public final static String OPSTRING = "MenosMenos";
    public MenosMenos(int fila, int columna) {
        super(fila, columna);
        exp = KindE.MENOSMENOS;
    }

    public String toString() {
        return MenosMenos.OPSTRING;
    }

    @Override
    public void inicializarTipado() {
        /* int -> int */
        /* boolean -> boolean */
        tipado = new ArrayList<Set<NodoTipo>>() {{
            add(new LinkedHashSet<>(Arrays.asList( new Entero())));
            add(new LinkedHashSet<>(Arrays.asList( new Entero())));
        }};
    }
}
