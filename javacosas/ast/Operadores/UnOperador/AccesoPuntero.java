package ast.Operadores.UnOperador;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ast.Tipos.Tipado;
import ast.Tipos.Tipado.TiposEnum;

public class AccesoPuntero extends OperadorUn{
    //TODO a qué tipos puede apuntar un puntero??
    public AccesoPuntero(){
        this.inicializarTipado();
    }

    public String toString() {
        return "AccesoPuntero";
    }

    @Override
    public void inicializarTipado() {
        /* t^ -> t */
        List<Set<TiposEnum>> tipo = new ArrayList<Set<TiposEnum>>();
        tipo.add(Tipado.TIPOS_PUNTERO);
        tipo.add(Tipado.TIPOS_PUNTERO);
    }
}
