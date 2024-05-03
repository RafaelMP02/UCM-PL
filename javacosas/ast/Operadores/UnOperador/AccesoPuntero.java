package ast.Operadores.UnOperador;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import ast.Tipos.Tipado;
import ast.Tipos.Tipado.TiposEnum;

public class AccesoPuntero extends OperadorUn{
    public final static String OPSTRING = "AccesoPuntero";
    public AccesoPuntero(int fila, int columna){
        super(fila, columna);
        this.inicializarTipado();
    }

    public String toString() {
        return AccesoPuntero.OPSTRING;
    }

    @Override
    public void inicializarTipado() {
        /* t^ -> t */
        tipado = new HashSet<List<TiposEnum>>();
        for (TiposEnum t : Tipado.TIPOS_PUNTERO) {
            List<TiposEnum> lista_tipos = new ArrayList<TiposEnum>(Arrays.asList(t, t));
            tipado.add(lista_tipos);
        }
    }
}
