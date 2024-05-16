package ast.Operadores.UnOperador;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import ast.Expresiones.KindE;
import ast.Tipos.Entero;
import ast.Tipos.NodoTipo;

public class MasMas extends OperadorUn{
    /* Operador incremento */
    public final static String OPSTRING = "MásMás";
    public MasMas(int fila, int columna) {
        super(fila, columna);
        exp = KindE.MASMAS;
    }

    public String toString() {
        return MasMas.OPSTRING;
    }

    @Override
    public void inicializarTipado() {
        /* int -> int */
        tipado = new ArrayList<Set<NodoTipo>>() {{
            add(new LinkedHashSet<>(Arrays.asList( new Entero())));
            add(new LinkedHashSet<>(Arrays.asList( new Entero())));
        }};
    }
}
