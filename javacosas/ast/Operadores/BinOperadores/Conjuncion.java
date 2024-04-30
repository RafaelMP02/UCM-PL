package ast.Operadores.BinOperadores;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ast.Tipos.Tipado.TiposEnum;

public class Conjuncion extends OperadorBin{
    public Conjuncion(){
        this.inicializarTipado();
    }

    public String toString() {
        return "Conjunción";
    }

    @Override
    public void inicializarTipado() {
        /* boolean -> boolean -> boolean */
        List<Set<TiposEnum>> tipo = new ArrayList<Set<TiposEnum>>();
        Set<TiposEnum> param1 = new HashSet<TiposEnum>(Set.of(TiposEnum.BOOLEANO));
        Set<TiposEnum> param2 = new HashSet<TiposEnum>(Set.of(TiposEnum.BOOLEANO));
        Set<TiposEnum> res = new HashSet<TiposEnum>(Set.of(TiposEnum.BOOLEANO));
        tipo.add(param1);
        tipo.add(param2);
        tipo.add(res);
    }
}
