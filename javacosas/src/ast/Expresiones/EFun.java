package ast.Expresiones;

import ast.GeneracionCodigo.Comp;
import ast.Instruccion;
import ast.Tipos.NodoTipo;
import ast.Tipos.Tipado;
import ast.Vinculacion.Vinculacion;

import java.util.LinkedList;
import java.util.Set;

public class EFun extends E implements Instruccion {
    private E idFuncion;
    private LinkedList<E> parametros;

    public EFun(E id, LinkedList<E> parametros, int fila, int columna) {
        super(fila, columna);
        this.idFuncion = id;
        this.parametros = parametros;
        asignable = false;
        exp = KindE.ACCFUN;
    }

    public String toString() {
       return idFuncion.toString() + parametros.toString();
    }

    @Override
    public void bind(Vinculacion vinc) {
        idFuncion.bind(vinc);
        for (E parametro : parametros) {
            parametro.bind(vinc);
        }
    }

    @Override
    public Set<NodoTipo> type(Set<NodoTipo> tiposEsperados) {
        Set<NodoTipo> tipado = Tipado.matchExpFunc(tiposEsperados, idFuncion, parametros, fila, columna);
        this.tipo = tipado.iterator().next();
        return tipado;
    }


    @Override
    public String codeE(Comp hcon) {
        return null;
    }

    @Override
    public String codeI(Comp hcom) {
        return null;
    }
}
