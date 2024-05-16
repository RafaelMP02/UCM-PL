package ast;


import ast.Expresiones.Identificador;
import ast.GeneracionCodigo.Comp;
import ast.Metaoperadores.Ambito;
import ast.Tipos.*;
import ast.Tipos.Tipado.TiposEnum;
import ast.Vinculacion.Vinculacion;

import java.util.*;

public class DecFuncion extends Declaracion {
    /* Define la función, es decir, la "instancia" porque se vincularán los identificadores iguales a este nodo.*/
    private LinkedList<Identificador> argumentosId;

    private LinkedList<DecVariable> argumentosDec;
    private Ambito ambito;

    public DecFuncion(Funcional tipo, Identificador id, LinkedList<Identificador> arg, Ambito ambito){
        super(tipo, id);
        List<Parametrico> l = tipo.getParametros();
        this.argumentosId = arg;
        int m = Math.min(l.size(), arg.size());
        Iterator<Parametrico> it_par = l.iterator();
        Iterator<Identificador> it_id = arg.iterator();
        Parametrico par;
        Identificador ide;
        for(int i = 0; i < m; i++) {
            par = it_par.next();
            ide = it_id.next();
            if(par.copia()){
                ide.setCopy();
            }
        }
        this.ambito = ambito;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append( " " + tipo.toString()  + " " + id.toString() + " (");
        if (argumentosId != null  && argumentosId.size() > 0) {
            str.append(argumentosId.get(0).toString());
            for (int i = 1; i < argumentosId.size(); i++) {
                str.append(" ," + argumentosId.get(i).toString());
            }
        }
        str.append(") " + ambito.toString());
        return str.toString();
    }

    @Override
    public void bind(Vinculacion vinc) {
        //Lo insertamos dos veces, para que pueda ser usado fuera y que dentro no se pueda declarar otra función igual
        int fila = id.getFila();
        int columna = id.getColumna();
        String idString = id.toString();
        vinc.insertaId(idString, this,fila, columna);
        vinc.abreBloque();
        vinc.insertaId(idString, this, fila, columna);
        vinc.bindFuncion(argumentosDec, argumentosId, ((Funcional) tipo).getParametros(), idString, fila, columna);
        this.ambito.bind(vinc);
        vinc.cierraBloque();
    }

    @Override
    public Set<NodoTipo> type(Set<NodoTipo> tiposEsperados) {
        //Si el tipo de retorno es void, no puede haber instrucciones de return.
        if (((Funcional) tipo).getTipoRetorno().typeToEnum().equals(TiposEnum.VOID))
            ambito.type(Tipado.enumToTipo(Tipado.TIPOS_INSTR_FUNC_VOID));
        //Puede haber instrucciones de tipo return
        else
            ambito.type(Tipado.enumToTipo(Tipado.TIPOS_INSTR_FUNC));
        return Tipado.matchDec(tipo, tiposEsperados, getFila(), getColumna());
    }

    @Override
    public NodoTipo getTipo() {
        return this.tipo;
    }

    @Override
    public String codeI(Comp hcom) {
        return null;
    }
}
