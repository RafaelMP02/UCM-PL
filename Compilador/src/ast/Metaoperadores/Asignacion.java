package ast.Metaoperadores;

import ast.Expresiones.E;
import ast.GeneracionCodigo.Comp;
import ast.Tipos.NodoTipo;
import ast.Tipos.Tipado;
import ast.Tipos.Tipado.TiposEnum;
import ast.Vinculacion.Vinculacion;

import java.util.Set;

import ast.Instruccion;

public class Asignacion implements Instruccion  {
    private CabecerAsig cabecera;
    private E exp;


    public Asignacion(CabecerAsig c, E e) {
        this.cabecera = c;
        this.exp = e;
    }

    public String toString() {
        return cabecera.toString() + " := " + exp.toString();
    }
    @Override
    public void bind(Vinculacion vinc) {
        exp.bind(vinc); //Vinculamos la segunda expresión primero para que no se use el elemento declarado en su asignación
        cabecera.bind(vinc);
    }
    @Override
    public Set<NodoTipo> type(Set<NodoTipo> tipoEsperado) {
        /* Hacemos match de la expresión y la cabecera y devolvemos el conjunto de tipos que funcionan.*/   
        return Tipado.matchAsig(tipoEsperado, cabecera, exp, getFila(), getColumna());
    }

    @Override
    public int getFila() {
        return  cabecera.getFila();
    }

    @Override
    public int getColumna() {
        return cabecera.getColumna();
    }

    @Override
    public String codeI(Comp hcon) {
        StringBuilder s = new StringBuilder();
        if(cabecera.tip().typeToEnum() == TiposEnum.STRUCT) {
            s.append("global.get $SP").append("local.get $i");
            s.append(exp.codeB(hcon));
            s.append("global.get $SP").append(cabecera.codeD(hcon)).append("i32.const ").append(4*cabecera.tip().getTam()).append("\n");
            s.append("call $copy_memory");
        } else if(cabecera.tip().typeToEnum() == TiposEnum.ARRAY) {
            s.append("global.get $SP").append("local.get $i");
            s.append(exp.codeB(hcon));
            s.append("global.get $SP").append(cabecera.codeD(hcon)).append("i32.const ").append(4*cabecera.tip().getTam()).append("\n");
            s.append("call $copy_memory");
        } else {
            s .append( cabecera.codeD(hcon) ).append( exp.codeE(hcon) ).append( "i32.store\n");
        }
        return s.toString();
    }
}
