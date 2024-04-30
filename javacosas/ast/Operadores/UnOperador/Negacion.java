package ast.Operadores.UnOperador;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ast.Tipos.Tipado.TiposEnum;

public class Negacion extends OperadorUn{
    /* Operador decremento */
    public Negacion(){
        this.inicializarTipado();
    }

    public String toString() {
        return "Negación";
    }

    @Override
    public void inicializarTipado() {
        /* boolean -> boolean */
        List<Set<TiposEnum>> tipo = new ArrayList<Set<TiposEnum>>();
        Set<TiposEnum> param = new HashSet<TiposEnum>(Set.of(TiposEnum.BOOLEANO));
        Set<TiposEnum> res = new HashSet<TiposEnum>(Set.of(TiposEnum.BOOLEANO));
        tipo.add(param);
        tipo.add(res);
    }
}
