package ast.Operadores.UnOperador;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import ast.Tipos.Tipado.TiposEnum;

public class MenosMenos extends OperadorUn{
    /* Operador decremento */
    public final static String OPSTRING = "MenosMenos";
    public MenosMenos(){
        this.inicializarTipado();
    }

    public String toString() {
        return MenosMenos.OPSTRING;
    }

    @Override
    public void inicializarTipado() {
        /* int -> int */
        tipado = new HashSet<List<TiposEnum>>();
        List<TiposEnum> lista_tipos = new ArrayList<TiposEnum>(Arrays.asList(TiposEnum.ENTERO, TiposEnum.ENTERO));
        tipado.add(lista_tipos);
    }
}
