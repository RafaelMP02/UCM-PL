package ast.Operadores.BinOperadores;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ast.Tipos.Tipado;
import ast.Tipos.Tipado.TiposEnum;

public class Igual extends OperadorBin{
    public Igual(){
    }

    public String toString() {
        return "Igual";
    }

    @Override
    public void inicializarTipado() {
        /* tConCampos -> int -> tCampos */
        List<Set<TiposEnum>> tipo = new ArrayList<Set<TiposEnum>>();
        Set<TiposEnum> param1 = new HashSet<TiposEnum>(Tipado.TIPOS_CON_CAMPOS);
        Set<TiposEnum> param2 = new HashSet<TiposEnum>(Set.of(TiposEnum.ENTERO));
        Set<TiposEnum> res = new HashSet<TiposEnum>(Tipado.TIPOS_CAMPOS);
        tipo.add(param1);
        tipo.add(param2);
        tipo.add(res);
    }
}
