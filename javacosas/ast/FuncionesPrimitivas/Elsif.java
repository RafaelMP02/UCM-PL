package ast.FuncionesPrimitivas;

import ast.ASTNode;
import ast.LocatedNode;
import ast.Expresiones.E;
import ast.Metaoperadores.Ambito;
import ast.Vinculacion.Vinculacion;
import ast.NodeKind;

public class Elsif extends LocatedNode implements ASTNode {
    private Elsif siguienteElsif = null;
    private Else siguienteElse  = null;

    //ambos o uno deben de ser nulos

    private Ambito ambito;
    private E cond;

    public Elsif(E cond, Ambito amb, Elsif siguienteElsif, Else siguienteElse, int fila, int columna) {
        super(fila, columna);
        this.cond = cond;
        this.ambito = amb;
        this.siguienteElsif = siguienteElsif;
        this.siguienteElse = siguienteElse;
    }

    public NodeKind nodeKind() {
        return NodeKind.ELSIF;
    }

    public String toString() {
        if(siguienteElsif != null) {
            return "ELSIF(" + cond.toString() + ")" + ambito.toString()  + siguienteElsif.toString();
        } else if (siguienteElse != null) {
            return "ELSIF(" + cond.toString() + ")" + ambito.toString()  + siguienteElse.toString();
        } else {
            return "ELSIF(" + cond.toString() + ")" + ambito.toString();
        }
    }

    @Override
    public void bind(Vinculacion vinc) {
        vinc.abreBloque();
        ambito.bind(vinc);
        vinc.cierraBloque();
        siguienteElsif.bind(vinc);
        siguienteElse.bind(vinc);
    }
}
