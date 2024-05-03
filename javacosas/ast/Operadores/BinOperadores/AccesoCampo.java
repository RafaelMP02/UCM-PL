package ast.Operadores.BinOperadores;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import ast.Tipos.Tipado;
import ast.Tipos.Tipado.TiposEnum;

public class AccesoCampo extends OperadorBin {
    public final static String OPSTRING = "AccesoCampo";
    public AccesoCampo(int fila, int columna){
        super(fila, columna);
        this.inicializarTipado();
    }
    public String toString() {
        return AccesoCampo.OPSTRING;
    }
    @Override
    public void inicializarTipado() {
        /* 
        struct -> tAtributo 
        class -> tAtributo
        class -> tFuncional
        */

        tipado = new HashSet<List<TiposEnum>>();
        for (TiposEnum t : Tipado.TIPOS_ATRIBUTOS) {
            List<TiposEnum> lista_tipos_struct = new ArrayList<TiposEnum>(Arrays.asList(TiposEnum.STRUCT, t));
            List<TiposEnum> lista_tipos_clase = new ArrayList<TiposEnum>(Arrays.asList(TiposEnum.CLASE, t));
            tipado.add(lista_tipos_struct);
            tipado.add(lista_tipos_clase);
        }
        tipado.add(Arrays.asList(TiposEnum.CLASE, TiposEnum.FUNCIONAL));
    }
}
