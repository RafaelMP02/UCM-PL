package ast.Operadores.BinOperadores;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import ast.Tipos.Tipado.TiposEnum;

public class Disyuncion extends OperadorBin {
    public final static String OPSTRING = "Disyunción";

    public Disyuncion() {
    }

    public String toString() {
        return Disyuncion.OPSTRING;
    }

    @Override
    public void inicializarTipado() {
        /* boolean -> boolean -> boolean */
        tipado = new HashSet<List<TiposEnum>>();
        List<TiposEnum> lista_tipos = new ArrayList<TiposEnum>(Arrays.asList(TiposEnum.BOOLEANO, TiposEnum.BOOLEANO, TiposEnum.BOOLEANO));
        tipado.add(lista_tipos);
    }
}
