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
        return null;
    }




    public String toString() {
        return "NEW";
    }

    @Override
    public Set<NodoTipo> type(Set<NodoTipo> tiposEsperados) {
        return Tipado.matchTipoEsperado(new TInstruccion(TiposEnum.OTRA_INSTRUCCION), tiposEsperados, fila, columna); //FIXME esto está bien??
    }

}
