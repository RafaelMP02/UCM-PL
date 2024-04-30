package ast.Operadores.UnOperador;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ast.Tipos.Tipado.TiposEnum;

public class Mas extends OperadorUn{
    /* Operador de números positivos */
    public Mas(){
        this.inicializarTipado();
    }

    public String toString() {
        return "Más";
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
