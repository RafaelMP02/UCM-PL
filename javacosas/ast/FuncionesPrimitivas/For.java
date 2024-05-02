package ast.FuncionesPrimitivas;

import ast.Expresiones.E;
import ast.Metaoperadores.Ambito;
import ast.Metaoperadores.Asignacion;
import ast.Tipos.NodoTipo;
import ast.Tipos.Tipado;
import ast.Vinculacion.Vinculacion;

import java.util.Set;

import ast.LocatedNode;
import ast.TiposDeNodos;
import ast.Programa;

public class For extends LocatedNode implements Programa {
    private Asignacion inicio;
    private Asignacion paso;

    private E fin;

    private Ambito ambito;

    private Programa programa;

   public For(Asignacion inicio, Asignacion paso, E fin, Ambito amb, Programa p, int fila, int columna) {
        super(fila, columna);
       this.paso = paso;
       this.fin = fin;
       this.ambito = amb;
       this.programa = p;
   }
    public TiposDeNodos nodeKind() {
        return TiposDeNodos.FOR;
    }
    public String toString() {
        return "FOR(" + inicio.toString() +";" + fin.toString() + ";" + paso.toString() + ")" + ambito.toString() + programa.toString();
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
        Tipado.matchEBool(fin);
        programa.type();
        return null;
    }
}
