package ast.FuncionesPrimitivas;

import ast.Expresiones.E;
import ast.Metaoperadores.Ambito;
import ast.Tipos.NodoTipo;
import ast.Tipos.Tipado;
import ast.Vinculacion.Vinculacion;

import java.util.Set;

import ast.LocatedNode;
import ast.TiposDeNodos;
import ast.Programa;


public class While extends LocatedNode implements Programa {
    E cond;
    Ambito ambito;
    Programa programa;

    public While(E cond, Ambito amb, Programa p, int fila, int columna) {
        super(fila, columna);
        this.cond = cond;
        this.ambito = amb;
        this.programa = p;
    }

    public TiposDeNodos nodeKind() {
        return TiposDeNodos.WHILE;
    }
    public String toString() {
        if( cond != null) {
            return "WHILE(" + cond.toString() + ")" + ambito.toString() + programa.toString();
        } else {
            return "WHILE(#ERROR#)" + ambito.toString() + programa.toString();
        }
    }

    @Override
    public void bind(Vinculacion vinc) {
        vinc.abreBloque();
        ambito.bind(vinc);
        vinc.cierraBloque();
        programa.bind(vinc);
    }

    @Override
    public Set<NodoTipo> type() {
        Tipado.matchEBool(cond);
        ambito.type();
        programa.type();
        return null;
    }
}
