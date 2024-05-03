package ast.Operadores.BinOperadores;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import ast.Tipos.Tipado.TiposEnum;

public class Producto extends OperadorBin {
    public final static String OPSTRING = "Producto";
    public Producto(int fila, int columna){
        super(fila, columna);
        this.inicializarTipado();
    }

    public String toString() {
        return Producto.OPSTRING;
    }

    @Override
    public void inicializarTipado() {
        /* int -> int -> int */
        tipado = new HashSet<List<TiposEnum>>();
        List<TiposEnum> lista_tipos = new ArrayList<TiposEnum>(Arrays.asList(TiposEnum.ENTERO, TiposEnum.ENTERO, TiposEnum.ENTERO));
        tipado.add(lista_tipos);
    }
}
