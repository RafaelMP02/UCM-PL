package ast.FuncionesPrimitivas;


import java.util.Set;

import ast.Expresiones.E;
import ast.GeneracionCodigo.Comp;
import ast.Tipos.NodoTipo;
import ast.Tipos.TInstruccion;
import ast.Tipos.Tipado;
import ast.Tipos.Tipado.TiposEnum;

public class New extends E { //New no debería ser un metaoperador??

    public New(int fila, int columna) {
        super(fila, columna);
    }

    @Override
    public String codeE(Comp hcon) {

        StringBuilder s = new StringBuilder();
        int c = hcon.setNew();
        s.append("i32.const ").append(4*c).append("\n");
        s.append("global.set $NP\n");
        s.append("global.get $NP\n");
        return s.toString();
    }






    public String toString() {
        return "NEW";
    }

    @Override
    public Set<NodoTipo> type(Set<NodoTipo> tiposEsperados) {
        return Tipado.matchTipoEsperado(new TInstruccion(TiposEnum.OTRA_INSTRUCCION), tiposEsperados, fila, columna); //FIXME esto está bien??
    }

}
