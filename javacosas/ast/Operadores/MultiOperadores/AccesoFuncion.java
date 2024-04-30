package ast.Operadores.MultiOperadores;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import ast.Operadores.Operador;
import ast.Tipos.Tipado;
import ast.Tipos.Tipado.TiposEnum;

public class AccesoFuncion extends Operador {

    public AccesoFuncion(){
        this.inicializarTipado();
    }

    public String toString() {
        return "AccesoFunción";
    }

    @Override
    public void inicializarTipado() {
        /* tFuncional -> tResFuncion */
        List<Set<TiposEnum>> tipo = new ArrayList<Set<TiposEnum>>();
        tipo.add(Set.of(TiposEnum.FUNCIONAL));
        tipo.add(Tipado.TIPOS_RES_FUNCIONES);
    }
}
