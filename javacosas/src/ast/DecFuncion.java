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
    public void bindFunc(Vinculacion vinc) {
        /* Es el único nodo que realiza algo en bindFunc, para poder usar así las declaraciones de funciones en cualquier punto del bloque */
        int fila = id.getFila();
        int columna = id.getColumna();
        String idString = id.toString();
        vinc.insertaId(idString, this,fila, columna);
    }

    @Override
    public void bind(Vinculacion vinc) {
        /* Insertamos nuevas declaraciones en un bloque intermedio para poder vincular los parámetros a estas declaraciones. Después vinculamos el ámbito. */        
        int fila = id.getFila();
        int columna = id.getColumna();
        String idString = id.toString();
        vinc.abreAmbFunc();
        vinc.bindParam(argumentosDec, argumentosId, ((Funcional) tipo).getParametros(), idString, fila, columna);
        this.ambito.bind(vinc);
        vinc.cierraAmbFunc();
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
        return "";
    }

    @Override
    public String codeFunc(Comp hcon){
        StringBuilder s = new StringBuilder(ambito.codeFunc(hcon));
        String fun = "main";
        if(!this.id.toString().equals("main")) {
            hcon.insertarFunc(this);
            fun = hcon.buscarFun(this);
        } else{
            hcon.insertarMain(this);
        }
        s.append("( func " + fun + "\n");
        if(this.tipo.typeToEnum() != TiposEnum.VOID){
            s.append("(result i32\n");
        }
        Map<Declaracion, String> parametros = new LinkedHashMap<>();
        s.append(hcon.getGLSTR()); //GLOBAL STRING
        Map<String, LinkedHashSet<Declaracion>> set = hcon.getAtributos();
        int num_campos = 1;
        for(String atri: set.keySet()) {
            for(Declaracion dec: set.get(atri)) {
                s.append(" (local $campo").append(num_campos).append(" i32)\n");
                parametros.put(dec, "campo" + Integer.toString(num_campos));
                num_campos = num_campos + 1;
            }
        }
        Iterator<DecVariable> it = argumentosDec.iterator();
        int num_param = 1;
        while(it.hasNext()) {
            s.append(" (local $param").append(num_param).append(" i32)\n");
            parametros.put(it.next(), "$param" + Integer.toString(num_param));
            num_param = num_param + 1;
        }
        hcon.setLocalMap(parametros);
        s.append("(local $i i32)\n");
        s.append("(local $temp i32)\n");
        s.append("get_global $MP\n");
        s.append("i32.const 8\n");
        s.append("i32.add\n");
        s.append("tee_local $i\n");
        for(int i = 1; i <= set.size(); i++) {
            s.append("i32.load\n");
            s.append("set_local $campo").append(i).append("\n");
            s.append("get_local $i\n");
            s.append("i32.const 4\n");
            s.append("i32.add\n");
            s.append("tee_local $i\n");
        }
        for(int i = 1; i <= argumentosId.size(); i++) {
            s.append("i32.load\n");
            s.append("set_local $param").append(i).append("\n");
            s.append("get_local $i\n");
            s.append("i32.const 4\n");
            s.append("i32.add\n");
            s.append("tee_local $i\n");
        }
        s.append("drop\n");

        s.append(ambito.codeI(hcon)).append("return\n").append(")\n");
        hcon.clearLocalMap();
        return s.toString();
    }
}
