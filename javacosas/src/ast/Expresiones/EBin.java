package ast.Expresiones;
import java.util.Set;

import ast.GeneracionCodigo.Comp;
import ast.Operadores.BinOperadores.*;
import ast.Operadores.UnOperador.Negacion;
import ast.Tipos.NodoTipo;
import ast.Tipos.Tipado;
import ast.Vinculacion.Vinculacion;


public class EBin extends E {
    /* Expresión que incluye un operador binario como atributo */
    private E opnd1;
    private E opnd2;
    private OperadorBin operador;
    public EBin(E opnd1, E opnd2, OperadorBin operador) {
        super(operador.getFila(), operador.getColumna());
        this.opnd1 = opnd1;
        this.opnd2 = opnd2;
        this.operador = operador;
        this.exp = operador.getExp();
        if(exp == KindE.ACCCAMPO || exp == KindE.ACCARRAY)
            asignable = true;
        else 
            asignable = false;
    }
    public E opnd1() {return opnd1;}
    public E opnd2() {return opnd2;}

    public OperadorBin operador() {return operador;}


    public String toString() {
        return operador.toString() + "(" + opnd1.toString() + " , " + opnd2.toString() + ")";
    }

    @Override
    public void bind(Vinculacion vinc) {
        if (exp == KindE.ACCCAMPO) {
            /*
            Vinculación solo se encargará de encontrar si el objeto al que pertenece el campo existe. Esto lo hará recursivamente hasta llegar a un identificador. 
            En el tipado ya identificaremos si cada campo está bien tipado.
            */
            opnd1.bind(vinc);
        }
        else {
            opnd1.bind(vinc);
            opnd2.bind(vinc);
        }
    }

    @Override
    public Set<NodoTipo> type(Set<NodoTipo> tiposEsperados) {
        Set<NodoTipo> tipado = Tipado.matchEBin(tiposEsperados, opnd1, opnd2, operador);
        this.tipo = tipado.iterator().next();
        return tipado;
    }

    @Override
    public String codeE(Comp hcon) {
        E ex;
        String s = null;
        switch (exp){
            case SUMA:
                s = opnd1.codeE(hcon) + opnd2.codeE(hcon) + "i32.add\n";
                break;
            case RESTA:
                s = opnd1.codeE(hcon) + opnd2.codeE(hcon) + "i32.sub\n";
                break;
            case PROD:
                s = opnd1.codeE(hcon) + opnd2.codeE(hcon) + "i32.mul\n";
                break;
            case DIVISION:
                s = opnd1.codeE(hcon) + opnd2.codeE(hcon) + "i32.div_s\n";
                break;
            case CONJUNCION:
                s = opnd1.codeE(hcon) + opnd2.codeE(hcon) + "i32.or\n";
                break;
            case DISYUNCION:
                s = opnd1.codeE(hcon) + opnd2.codeE(hcon) + "i32.and\n";
                break;
            case IGUAL:
                s = opnd1.codeE(hcon) + opnd2.codeE(hcon) + "i32.eq\n";
                break;
            case DISTINTO:
                s = opnd1.codeE(hcon) + opnd2.codeE(hcon) + "i32.ne\n";
                break;
            case MENQ:
                s = opnd1.codeE(hcon) + opnd2.codeE(hcon) + "i32.lt\n";
                break;
            case MAYQ:
                s = opnd1.codeE(hcon) + opnd2.codeE(hcon) + "i32.gt\n";
                break;
            case MENIGUAL:
                ex = new EUn(new EBin(opnd1, opnd2, new MayorQue(this.fila, this.columna)), new Negacion(this.fila, this.columna));
                s = ex.codeE(hcon);
                break;
            case MAYIGUAL:
                ex = new EUn(new EBin(opnd1, opnd2, new MenorQue(this.fila, this.columna)), new Negacion(this.fila, this.columna));
                s = ex.codeE(hcon);
                break;
            case ACCARRAY:
                s = this.codeD(hcon);
                break;
            case ACCCAMPO:
                s = this.codeD(hcon);
                break;
        }
        return s;
    }

    @Override
    public  String codeD(Comp hcon){
        String s = null;

        switch(exp){
            case ACCARRAY:
                s = opnd1.codeD(hcon) + "i32.const " + Integer.toString(tipo.getTam()) + "\n" + opnd2.codeE(hcon) + "i32.mul\n" + "i32.add\n";
                break;
            case ACCCAMPO:
                break;

        }

        return  s;
    }

}
