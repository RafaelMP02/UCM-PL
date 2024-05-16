package ast.Operadores.BinOperadores;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import ast.Expresiones.KindE;
import ast.Tipos.Booleano;
import ast.Tipos.NodoTipo;

public class Conjuncion extends OperadorBin {
    public final static String OPSTRING = "Conjunción";
    public Conjuncion(int fila, int columna) {
        super(fila, columna);
        exp = KindE.CONJUNCION;
    }

    public String toString() {
        return Conjuncion.OPSTRING;
    }

    @Override
    public void inicializarTipado() {
        /* boolean -> boolean -> boolean */
        tipado = new ArrayList<Set<NodoTipo>>() {{
            add(new LinkedHashSet<>(Arrays.asList( new Booleano())));
            add(new LinkedHashSet<>(Arrays.asList( new Booleano())));
            add(new LinkedHashSet<>(Arrays.asList( new Booleano())));
        }};
    }
}
