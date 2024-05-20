package ast.Operadores.BinOperadores;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import ast.Expresiones.KindE;
import ast.Tipos.Entero;
import ast.Tipos.NodoTipo;

public class Suma extends OperadorBin {
    public final static String OPSTRING = "Suma";
    public Suma(int fila, int columna) {
        super(fila, columna);
        exp = KindE.SUMA;
    }

    public String toString() {
        return Suma.OPSTRING;
    }

    @Override
    public void inicializarTipado() {
        /* int -> int -> int */
        tipado = new ArrayList<Set<NodoTipo>>() {{
            add(new LinkedHashSet<>(Arrays.asList( new Entero())));
            add(new LinkedHashSet<>(Arrays.asList( new Entero())));
            add(new LinkedHashSet<>(Arrays.asList( new Entero())));
        }};
    }
}
