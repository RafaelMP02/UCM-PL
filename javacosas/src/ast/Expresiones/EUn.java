package ast.Expresiones;

import java.util.Set;

import ast.GeneracionCodigo.Comp;
import ast.Instruccion;
import ast.Operadores.BinOperadores.Resta;
import ast.Operadores.BinOperadores.Suma;
import ast.Operadores.UnOperador.AccesoPuntero;
import ast.Operadores.UnOperador.OperadorUn;
import ast.Tipos.NodoTipo;
import ast.Tipos.Tipado;
import ast.Vinculacion.Vinculacion;

public class EUn extends E implements Instruccion {
    /* Expresión de operadores unarios. */
    private E opnd;
    private OperadorUn operador;
    public EUn(E opnd, OperadorUn operador) {
        super(operador.getFila(), operador.getColumna());
        this.opnd = opnd;
        this.operador = operador;
        if (operador.toString().equals(AccesoPuntero.OPSTRING))
            asignable = true;
        else
            asignable = false;
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
    public Set<NodoTipo> type(Set<NodoTipo> tiposEsperados) {
        Set<NodoTipo> tipado = Tipado.matchEUn(tiposEsperados, opnd, operador);
        this.tipo = tipado.iterator().next();
        return tipado;
    }

    @Override
    public String codeE(Comp hcon) {
        E ex;
        StringBuilder s = new StringBuilder();
        switch (exp){
            case MAS:
                s.append(opnd.codeE(hcon));
                break;
            case MENOS:
                ex = new EBin(new Num("0"), opnd,  new Resta(this.fila, this.columna));
                s.append(ex.codeE(hcon));
                break;
            case MASMAS:
                ex = new EBin(opnd, new Num("1"), new Suma(this.fila, -1));
                s.append(opnd.codeD(hcon));
                s.append(ex.codeE(hcon));
                s.append("i32.store\n");
                break;
            case MENOSMENOS:
                ex = new EBin(opnd, new Num("1"), new Resta(this.fila, this.columna));
                s.append(opnd.codeD(hcon));
                s.append(ex.codeE(hcon));
                s.append("i32.store\n");
                break;
            case NEG:
                ex = new EBin(new Num("1"), opnd,  new Resta(this.fila, this.columna)); //1-x manda el 1 al 0 y el 0 al 1
                s.append(ex.codeE(hcon));
                break;
            case ACCPUN:
                s.append(opnd.codeE(hcon) + "i32.load\n");
                break;
        }
        return s.toString();
    }

    @Override
    public String codeI(Comp hcon) {
        return this.codeE(hcon);
    }
}