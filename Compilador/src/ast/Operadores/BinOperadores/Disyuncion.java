package ast.Operadores.BinOperadores;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import ast.Expresiones.KindE;
import ast.Tipos.Booleano;
import ast.Tipos.NodoTipo;

public class Disyuncion extends OperadorBin {
    public final static String OPSTRING = "Disyunción";

    public Disyuncion(int fila, int columna) {
        super(fila, columna);
        exp = KindE.DISYUNCION;
    }

    public String toString() {
        return Disyuncion.OPSTRING;
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
