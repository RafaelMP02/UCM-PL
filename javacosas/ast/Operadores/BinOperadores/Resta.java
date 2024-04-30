package ast.Operadores.BinOperadores;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ast.Tipos.Tipado.TiposEnum;

public class Resta extends OperadorBin{
    public Resta(){
    }

    public String toString() {
        return "Resta";
    }

    @Override
    public void inicializarTipado() {
        /* int -> int -> int */
        List<Set<TiposEnum>> tipo = new ArrayList<Set<TiposEnum>>();
        Set<TiposEnum> param1 = new HashSet<TiposEnum>(Set.of(TiposEnum.ENTERO));
        Set<TiposEnum> param2 = new HashSet<TiposEnum>(Set.of(TiposEnum.ENTERO));
        Set<TiposEnum> res = new HashSet<TiposEnum>(Set.of(TiposEnum.ENTERO));
        tipo.add(param1);
        tipo.add(param2);
        tipo.add(res);
    }
}
