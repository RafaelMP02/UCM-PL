package ast.Metaoperadores;

import ast.NodeKind;
import ast.Tipos.Parametrico;
import ast.Tipos.Tipo;
import ast.Vinculacion.Vinculacion;


public class AccesoValor implements Parametrico { // TODO por qué no es un operador normal??
    /* Operador en los argumentos de las funciones para que el paso de parámetro se realice por valor */
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

    @Override
    public void bind(Vinculacion vinc) {
        // TODO hay que hacer algo aquí?
    }
}
