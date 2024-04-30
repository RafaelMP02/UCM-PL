package ast.Operadores.BinOperadores;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ast.Tipos.Tipado;
import ast.Tipos.Tipado.TiposEnum;

public class Division extends OperadorBin{
    public Division(){
    }

    public String toString() {
        return "División";
    }

    @Override
    public void inicializarTipado() {
        /* tComparable -> tComparable -> booleano */
        List<Set<TiposEnum>> tipo = new ArrayList<Set<TiposEnum>>();
        Set<TiposEnum> param1 = new HashSet<TiposEnum>(Tipado.TIPOS_COMPARABLES);
        Set<TiposEnum> param2 = new HashSet<TiposEnum>(Tipado.TIPOS_COMPARABLES);
        Set<TiposEnum> res = new HashSet<TiposEnum>(Set.of(TiposEnum.BOOLEANO));
        tipo.add(param1);
        tipo.add(param2);
        tipo.add(res);
    }
}
