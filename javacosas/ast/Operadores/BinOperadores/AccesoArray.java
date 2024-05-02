package ast.Operadores.BinOperadores;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import ast.Tipos.Tipado;
import ast.Tipos.Tipado.TiposEnum;

public class AccesoArray extends OperadorBin{
    public final static String OPSTRING = "AccesoArray";
    public AccesoArray() {
        this.inicializarTipado();
    }

    @Override
    public String toString() {
        return AccesoArray.OPSTRING;
    }

    @Override
    public void inicializarTipado() {
        /* array -> int -> tContenidoArray */
        tipado = new HashSet<List<TiposEnum>>();
        for(TiposEnum t : Tipado.TIPOS_CONTENIDO_ARRAY) {
            List<TiposEnum> lista_tipos = new ArrayList<TiposEnum>(Arrays.asList(TiposEnum.ARRAY, TiposEnum.ENTERO, t));
            tipado.add(lista_tipos);
        }
    }

}
