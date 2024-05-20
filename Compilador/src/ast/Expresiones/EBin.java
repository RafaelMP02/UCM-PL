package ast.Expresiones;
import java.util.Set;

import ast.Declaracion;
import ast.Definicion;
import ast.GeneracionCodigo.Comp;
import ast.Operadores.BinOperadores.*;
import ast.Operadores.UnOperador.Negacion;
import ast.Tipos.NodoTipo;
import ast.Tipos.Tipado;
import ast.Tipos.TipoNuevo;
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

    public E getOpnd1() {
        return opnd1;
    }

    public E getOpnd2(){
        return opnd2;
    }

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
    public String codeI(Comp hcon){
        return this.codeE(hcon);
    }

    @Override
    public String codeE(Comp hcon) {
        E ex;
        StringBuilder s = new StringBuilder();
        switch (exp){
            case SUMA:
                s.append(opnd1.codeE(hcon)).append(opnd2.codeE(hcon) ).append( "i32.add\n");
                break;
            case RESTA:
                s .append( opnd1.codeE(hcon) ).append( opnd2.codeE(hcon) ).append( "i32.sub\n");
                break;
            case PROD:
                s .append( opnd1.codeE(hcon) ).append( opnd2.codeE(hcon) ).append( "i32.mul\n");
                break;
            case DIVISION:
                s .append( opnd1.codeE(hcon) ).append( opnd2.codeE(hcon) ).append( "i32.div_s\n");
                break;
            case CONJUNCION:
                s .append( opnd1.codeE(hcon) ).append( opnd2.codeE(hcon) ).append( "i32.or\n");
                break;
            case DISYUNCION:
                s .append( opnd1.codeE(hcon) ).append( opnd2.codeE(hcon) ).append( "i32.and\n");
                break;
            case IGUAL:
                s .append( opnd1.codeE(hcon) ).append( opnd2.codeE(hcon) ).append( "i32.eq\n");
                break;
            case DISTINTO:
                s .append( opnd1.codeE(hcon) ).append( opnd2.codeE(hcon) ).append( "i32.ne\n");
                break;
            case MENQ:
                s .append( opnd1.codeE(hcon) ).append( opnd2.codeE(hcon) ).append( "i32.lt\n");
                break;
            case MAYQ:
                s.append( opnd1.codeE(hcon) ).append( opnd2.codeE(hcon) ).append( "i32.gt\n");
                break;
            case MENIGUAL:
                ex = new EUn(new EBin(opnd1, opnd2, new MayorQue(this.fila, this.columna)), new Negacion(this.fila, this.columna));
                s .append( ex.codeE(hcon));
                break;
            case MAYIGUAL:
                ex = new EUn(new EBin(opnd1, opnd2, new MenorQue(this.fila, this.columna)), new Negacion(this.fila, this.columna));
                s .append( ex.codeE(hcon));
                break;
            case ACCARRAY:
                s .append( this.codeD(hcon)).append("i32.load\n");
                break;
            case ACCCAMPO:
                s .append( this.codeD(hcon)).append("i32.load\n");
                break;
        }
        return s.toString();
    }

    @Override
    public  String codeD(Comp hcon){
        StringBuilder s = new StringBuilder();

        switch(exp){
            case ACCARRAY:
                s.append(opnd1.codeD(hcon)).append("i32.const " ).append( 4*tipo.getTam()).append(  "\n" ).append( opnd2.codeE(hcon) ).append( "i32.mul\n" ).append( "i32.add\n");
                break;
            case ACCCAMPO:
                Identificador campo = (Identificador) opnd2;
                Declaracion dec = campo.getVinculo();
                Definicion def = ((TipoNuevo) opnd1.tipo).getDef();
                s.append(opnd1.codeD(hcon)).append("i32.const " ).append( hcon.buscaCampo(def, dec)).append(  "\n" ).append( "i32.add\n");
                break;

        }

        return  s.toString();
    }

}
