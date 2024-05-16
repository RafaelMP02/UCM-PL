package ast.Operadores.BinOperadores;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import ast.Expresiones.KindE;
import ast.Tipos.Array;
import ast.Tipos.Entero;
import ast.Tipos.NodoTipo;
import ast.Tipos.Tipado;

public class AccesoArray extends OperadorBin{
    public final static String OPSTRING = "AccesoArray";
    public AccesoArray(int fila, int columna) {
        super(fila, columna);
        exp = KindE.ACCARRAY;
    }

    @Override
    public String toString() {
        return AccesoArray.OPSTRING;
    }

    @Override
    public void inicializarTipado() {
        /* array -> int -> tContenidoArray */
        tipado = new ArrayList<Set<NodoTipo>>() {{
            add(new LinkedHashSet<>(Arrays.asList(new Array())));
            add(new LinkedHashSet<>(Arrays.asList(new Entero())));
            add(Tipado.enumToTipo(Tipado.TIPOS_CONTENIDO_ARRAY));
        }};
    }

}
