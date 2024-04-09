package ast.Metaoperadores;

import ast.NodeKind;
import ast.Tipos.Parametrico;
import ast.Tipos.Tipo;


public class AccesoValor implements Parametrico {
    private Tipo t;
    public AccesoValor(Tipo t){
       this.t = t;
    }

    @Override
    public String toString() {
        return "$ " + t.toString();
    }

    public NodeKind nodeKind() {
        return NodeKind.VALOR;
    }
}
