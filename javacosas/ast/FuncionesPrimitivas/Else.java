package ast.FuncionesPrimitivas;

import ast.ASTNode;
import ast.LocatedNode;
import ast.Metaoperadores.Ambito;
import ast.Vinculacion.Vinculacion;
import ast.NodeKind;

public class Else extends LocatedNode implements ASTNode {

    private Ambito ambito;

    public Else(Ambito amb, int fila, int columna) {
        super(fila, columna);
        this.ambito = amb;
    }

    public NodeKind nodeKind() {
        return NodeKind.ELSE;
    }

    public String toString() {
        return "ELSE" + ambito.toString();
    }

    @Override
    public void bind(Vinculacion vinc) {
        vinc.abreBloque();
        ambito.bind(vinc);
        vinc.cierraBloque();
    }
}
