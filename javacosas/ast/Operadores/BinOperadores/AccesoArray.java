package ast.Operadores.BinOperadores;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ast.Tipos.Tipado;
import ast.Tipos.Tipado.TiposEnum;

public class AccesoArray extends OperadorBin{
    public AccesoArray() {
        this.inicializarTipado();
    }

    @Override
    public String toString() {
        return "AccesoArray";
    }

    @Override
    public void inicializarTipado() {
        /* array -> int -> tContenidoArray */
        List<Set<TiposEnum>> tipo = new ArrayList<Set<TiposEnum>>();
        Set<TiposEnum> param1 = new HashSet<TiposEnum>(Set.of(TiposEnum.ARRAY));
        Set<TiposEnum> param2 = new HashSet<TiposEnum>(Set.of(TiposEnum.ENTERO));
        Set<TiposEnum> res = new HashSet<TiposEnum>(Tipado.TIPOS_CONTENIDO_ARRAY);
        tipo.add(param1);
        tipo.add(param2);
        tipo.add(res);
    }
}
