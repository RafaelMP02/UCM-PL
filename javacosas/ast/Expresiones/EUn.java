package ast.Expresiones;

import java.util.List;
import java.util.Set;

import ast.Instruccion;
import ast.Operadores.UnOperador.OperadorUn;
import ast.Tipos.NodoTipo;
import ast.Tipos.Tipado;
import ast.Vinculacion.Vinculacion;

public class EUn implements E, Instruccion {
    private E opnd;
    private OperadorUn operador;
    public EUn(E opnd, OperadorUn operador) {
        this.opnd = opnd;
        this.operador = operador;
    }
    public E opnd() {return opnd;}

    public OperadorUn operador() {return operador;}

    public String toString() {
        return operador.toString() + "(" + opnd.toString() + ")";
    }

    @Override
    public void bind(Vinculacion vinc) {
        opnd.bind(vinc);
    }

    @Override
    public Set<NodoTipo> type() {
        return Tipado.matchEUn(opnd.type(), operador);
    }
}