package ast.FuncionesPrimitivas;

import ast.Expresiones.E;
import ast.Metaoperadores.Ambito;
import ast.Metaoperadores.Asignacion;
import ast.NodeKind;
import ast.Programa;

public class For extends Programa {
    private Asignacion inicio;
    private Asignacion paso;

    private E fin;

    private Ambito amb;

    private Programa P;

   public For(Asignacion inicio, Asignacion paso, E fin, Ambito amb, Programa P){
       this.inicio = inicio;
       this.paso = paso;
       this.fin = fin;
       this.amb = amb;
       this.P = P;
   }
    public NodeKind nodeKind() {
        return NodeKind.FOR;
    }
    public String toString() {
        return "FOR(" + inicio.toString() +";" + fin.toString() + ";" + paso.toString() + ")" + amb.toString() + P.toString();
    }
}
