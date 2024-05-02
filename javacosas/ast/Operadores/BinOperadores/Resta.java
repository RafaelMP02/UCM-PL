package ast.Operadores.BinOperadores;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import ast.Tipos.Tipado.TiposEnum;

public class Resta extends OperadorBin {
    public final static String OPSTRING = "Resta";
    public Resta(){
    }

    public String toString() {
        return Resta.OPSTRING;
    }

    @Override
    public void inicializarTipado() {
       /* int -> int -> int */
        tipado = new HashSet<List<TiposEnum>>();
        List<TiposEnum> lista_tipos = new ArrayList<TiposEnum>(Arrays.asList(TiposEnum.ENTERO, TiposEnum.ENTERO, TiposEnum.ENTERO));
        tipado.add(lista_tipos);
    }
}
