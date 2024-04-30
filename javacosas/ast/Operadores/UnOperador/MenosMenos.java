package ast.Operadores.UnOperador;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ast.Tipos.Tipado.TiposEnum;

public class MenosMenos extends OperadorUn{
    /* Operador decremento */
    public MenosMenos(){
        this.inicializarTipado();
    }

    public String toString() {
        return "MenosMenos";
    }

    @Override
    public void inicializarTipado() {
        /* int -> int */
        List<Set<TiposEnum>> tipo = new ArrayList<Set<TiposEnum>>();
        Set<TiposEnum> param = new HashSet<TiposEnum>(Set.of(TiposEnum.ENTERO));
        Set<TiposEnum> res = new HashSet<TiposEnum>(Set.of(TiposEnum.ENTERO));
        tipo.add(param);
        tipo.add(res);
    }
}
