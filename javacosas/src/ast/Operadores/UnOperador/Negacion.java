package ast.Operadores.UnOperador;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import ast.Expresiones.KindE;
import ast.Tipos.Booleano;
import ast.Tipos.NodoTipo;

public class Negacion extends OperadorUn{
    /* Operador decremento */
    public final static String OPSTRING = "Negación";
    public Negacion(int fila, int columna) {
        super(fila, columna);
        exp = KindE.NEG;
    }

    public String toString() {
        return Negacion.OPSTRING;
    }

    @Override
    public void inicializarTipado() {
        /* boolean -> boolean */
        tipado = new ArrayList<Set<NodoTipo>>() {{
            add(new LinkedHashSet<>(Arrays.asList( new Booleano())));
            add(new LinkedHashSet<>(Arrays.asList( new Booleano())));
        }};
    }
}
