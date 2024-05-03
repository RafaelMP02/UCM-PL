package ast.Operadores.UnOperador;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import ast.Tipos.Tipado.TiposEnum;

public class Menos extends OperadorUn{
    /* Operador de números negativos */
    public final static String OPSTRING = "Menos";

    public Menos(int fila, int columna){
        super(fila, columna);
        this.inicializarTipado();
    }

    public String toString() {
        return Menos.OPSTRING;
    }

    @Override
    public void inicializarTipado() {
        /* int -> int */
        tipado = new HashSet<List<TiposEnum>>();
        List<TiposEnum> lista_tipos = new ArrayList<TiposEnum>(Arrays.asList(TiposEnum.ENTERO, TiposEnum.ENTERO));
        tipado.add(lista_tipos);
    }
}
