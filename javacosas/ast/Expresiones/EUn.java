package ast.Expresiones;

import ast.KindE;
import ast.Operadores.UnOperador.OperadorUn;
import ast.Vinculacion.Vinculacion;

public class EUn implements E {
    private E opnd;
    private OperadorUn operador;
    public EUn(E opnd, OperadorUn operador) {
        this.opnd = opnd;
        this.operador = operador;
    }
    public E opnd() {return opnd;}

    public OperadorUn operador() {return operador;}

    public KindE kind() {
        return operador.kind();
    }

    public String toString() {
        return operador.toString() + "(" + opnd.toString() + ")";
    }
    @Override
    public void bind(Vinculacion vinc) {
        opnd.bind(vinc);
    }
}