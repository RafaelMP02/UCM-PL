package ast.FuncionesPrimitivas;

import ast.ASTNode;
import ast.LocatedNode;
import ast.Expresiones.Num;
import ast.Metaoperadores.Ambito;
import ast.Vinculacion.Vinculacion;
import ast.NodeKind;


public class Case extends LocatedNode implements ASTNode {
    private Num cond;
    private Case caso;
    private Ambito ambito;

    public Case(Num cond, Ambito amb, Case caso, int fila, int columna) {
        super(fila, columna);
        this.cond = cond;
        this.ambito= amb;
        this.caso = caso;
    }

    public NodeKind nodeKind() {
        return NodeKind.CASE;
    }
    public String toString() {
        if(caso != null) {
            return "CASE " + cond.toString() + ambito.toString() + caso.toString();
        } else {
            return "CASE " + cond.toString() + ambito.toString();
        }
    }

    @Override
    public void bind(Vinculacion vinc) {
        vinc.abreBloque();
        ambito.bind(vinc);
        vinc.cierraBloque();
    }
}
