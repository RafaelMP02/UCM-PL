package ast.Operadores.BinOperadores;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import ast.Tipos.Tipado;
import ast.Tipos.Tipado.TiposEnum;

public class MayorIgual extends OperadorBin {
    public final static String OPSTRING = "MayorIgual";
    public MayorIgual(){
    }

    public String toString() {
        return MayorIgual.OPSTRING;
    }

    @Override
    public void inicializarTipado() {
        /* tComparable -> tComparable -> booleano */
        tipado = new HashSet<List<TiposEnum>>();
        for (TiposEnum t : Tipado.TIPOS_COMPARABLES) {
            List<TiposEnum> lista_tipos = new ArrayList<TiposEnum>(Arrays.asList(t, t, TiposEnum.BOOLEANO));
            tipado.add(lista_tipos);
        }
    }
}
