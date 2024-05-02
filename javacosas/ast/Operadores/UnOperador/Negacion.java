package ast.Operadores.UnOperador;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import ast.Tipos.Tipado.TiposEnum;

public class Negacion extends OperadorUn{
    /* Operador decremento */
    public final static String OPSTRING = "Negación";
    public Negacion(){
        this.inicializarTipado();
    }

    public String toString() {
        return Negacion.OPSTRING;
    }

    @Override
    public void inicializarTipado() {
        /* boolean -> boolean */
        tipado = new HashSet<List<TiposEnum>>();
        List<TiposEnum> lista_tipos = new ArrayList<TiposEnum>(Arrays.asList(TiposEnum.BOOLEANO, TiposEnum.BOOLEANO));
        tipado.add(lista_tipos);
    }
}
