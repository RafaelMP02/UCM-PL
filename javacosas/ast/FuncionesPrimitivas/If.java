package ast.FuncionesPrimitivas;

import ast.Expresiones.E;
import ast.Metaoperadores.Ambito;
import ast.Vinculacion.Vinculacion;
import ast.LocatedNode;
import ast.NodeKind;
import ast.Programa;

public class If extends LocatedNode implements Programa {

    private Elsif siguienteIf = null;
    private Else siguienteElse  = null;

    //ambos o uno deben de ser nulos

    private Ambito ambito;
    private E cond;

    private Programa programa;

    public If(E cond, Ambito Amb, Programa p,Elsif siguienteIf, Else siguienteElse, int fila, int columna) {
        super(fila, columna);
        this.cond = cond;
        this.ambito = Amb;
        this.siguienteIf = siguienteIf;
        this.siguienteElse = siguienteElse;
        this.programa = p;
    }

    public NodeKind nodeKind() {
        return NodeKind.IF;
    }

    public String toString() {
        if(cond != null) {
            if (siguienteIf != null) {
                return "IF(" + cond.toString() + ")" + ambito.toString() + siguienteIf.toString() + programa.toString();
            } else if (siguienteElse != null) {
                return "IF(" + cond.toString() + ")" + ambito.toString() + siguienteElse.toString() + programa.toString();
            } else {
                return "IF(" + cond.toString() + ")" + ambito.toString() + programa.toString();
            }
        } else {
            return "#ERROR#" + programa.toString();
        }
    }

    @Override
    public void bind(Vinculacion vinc) {
        vinc.abreBloque();
        ambito.bind(vinc);
        vinc.cierraBloque();
        programa.bind(vinc);
    }
}
