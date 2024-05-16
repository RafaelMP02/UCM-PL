package ast.Operadores.UnOperador;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import ast.Expresiones.KindE;
import ast.Tipos.NodoTipo;
import ast.Tipos.Puntero;
import ast.Tipos.Tipado;

public class AccesoPuntero extends OperadorUn{
    public final static String OPSTRING = "AccesoPuntero";
    public AccesoPuntero(int fila, int columna) {
        super(fila, columna);
        exp = KindE.ACCPUN;
    }

    public String toString() {
        return AccesoPuntero.OPSTRING;
    }

    @Override
    public void inicializarTipado() {
        /* t^ -> t */
        tipado = new ArrayList<Set<NodoTipo>>() {{
            add(new LinkedHashSet<>(Arrays.asList( new Puntero())));
            add(Tipado.enumToTipo(Tipado.TIPOS_PUNTERO));
        }};
    }
}
