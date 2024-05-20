package ast.Operadores.BinOperadores;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import ast.Expresiones.KindE;
import ast.Tipos.Entero;
import ast.Tipos.NodoTipo;

public class Producto extends OperadorBin {
    public final static String OPSTRING = "Producto";
    public Producto(int fila, int columna) {
        super(fila, columna);
        exp = KindE.PROD;
    }

    public String toString() {
        return Producto.OPSTRING;
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
