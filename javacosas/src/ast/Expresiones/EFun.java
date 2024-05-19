package ast.Expresiones;

import ast.Declaracion;
import ast.Definicion;
import ast.GeneracionCodigo.Comp;
import ast.Instruccion;
import ast.Tipos.*;
import ast.Vinculacion.Vinculacion;

import java.util.*;

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
        StringBuilder s = new StringBuilder();
        Funcional f = (Funcional) idFuncion.tipo;
        List<Parametrico> l = f.getParametros();
        s.append(hcon.getFUNSTR());
        if(idFuncion.exp == KindE.CONST){ //identificador
            Identificador id = (Identificador) idFuncion;
            Declaracion dec = id.getVinculo();

            Iterator<Parametrico> it_p = l.iterator();
            Iterator<E> it_e = parametros.iterator();
            s.append("get_global $SP\n").append("tee_local $i\n");
            int c = parametros.size();
            s.append("i32.const ").append(4*c).append("\n").append("i32.add\n");
            while(it_p.hasNext()) {
                Parametrico t = it_p.next();
                E e = it_e.next();
                if(t.copia()) {
                    s.append("get_global $SP\n").append("get_local $i\n").append("i32.store\n");
                    if(e.asignable) {
                        s.append("call \n(").append(e.codeD(hcon)).append(")\n (get_global $SP)\n").append("(i32.const ").append(
                                4 * e.tipo.getTam()).append(")\n");
                        s.append("get_global $SP\n").append("i32.const ").append(4 * e.tipo.getTam()).append("\n").append("i32.add\n").append("set_global $SP\n");
                    } else {
                        s.append(e.codeE(hcon)).append("get_global $SP\n").append("(i32.store ");
                        s.append("get_global $SP\n").append("i32.const ").append(4).append("\n").append("i32.add\n").append("set_global $SP\n");
                    }
                    s.append("get_local $i\n").append("i32.const ").append(4).append("\n").append("i32.add\n").append("set_local $i\n");
                } else {
                    s.append(e.codeD(hcon)).append("get_local $i\n").append("(i32.store\n");
                    s.append("get_local $i\n").append("i32.const ").append(4).append("\n").append("i32.add\n").append("set_local $i\n");
                }
            }

            s.append("call ").append(hcon.buscarFun(dec)).append("\n");

        } else { //metodo
            EBin id = (EBin) idFuncion;
            Identificador campo = (Identificador) id.getOpnd2();
            Declaracion de = campo.getVinculo();
            E co = id.getOpnd1();
            Definicion def = ((TipoNuevo) co.tipo).getDef();

            Map<String, LinkedHashSet<Declaracion>> set = ((TipoNuevo) co.tipo).getCampos();
            int c = 0;
            for(String atri: set.keySet()) {
                for(Declaracion dec: set.get(atri)) {
                    c = c + 1;
                }
            }
            c = c + parametros.size();

            s.append("get_global $SP\n").append("tee_local $i\n");
            s.append("i32.const ").append(4*c).append("\n").append("i32.add\n");
            for(String atri: set.keySet()) {
                for(Declaracion dec: set.get(atri)) {
                    s.append(co.codeD(hcon)).append(hcon.buscaCampo(def, dec)).append("i32.add\n").append("get_local $i\n").append("(i32.store\n");
                    s.append("get_local $i\n").append("i32.const ").append(4).append("\n").append("i32.add\n").append("set_local $i\n");
                }
            }



            Iterator<Parametrico> it_p = l.iterator();
            Iterator<E> it_e = parametros.iterator();
            while(it_p.hasNext()) {
                Parametrico t = it_p.next();
                E e = it_e.next();
                if(t.copia()) {
                    s.append("get_global $SP\n").append("get_local $i\n").append("i32.store\n");
                    if(e.asignable) {
                        s.append("call \n(").append(e.codeD(hcon)).append(")\n (get_global $SP)\n").append("(i32.const ").append(
                                4 * e.tipo.getTam()).append(")\n");
                        s.append("get_global $SP\n").append("i32.const ").append(4 * e.tipo.getTam()).append("\n").append("i32.add\n").append("set_global $SP\n");
                    } else {
                        s.append(e.codeE(hcon)).append("get_global $SP\n").append("(i32.store ");
                        s.append("get_global $SP\n").append("i32.const ").append(4).append("\n").append("i32.add\n").append("set_global $SP\n");
                    }
                    s.append("get_local $i\n").append("i32.const ").append(4).append("\n").append("i32.add\n").append("set_local $i\n");
                } else {
                    s.append(e.codeD(hcon)).append("get_local $i\n").append("(i32.store\n");
                    s.append("get_local $i\n").append("i32.const ").append(4).append("\n").append("i32.add\n").append("set_local $i\n");
                }
            }

            s.append("call ").append(hcon.buscarFun(de)).append("\n");

        }
        return s.toString();
    }

    @Override
    public String codeI(Comp hcom) {
        return this.codeE(hcom);
    }
}
