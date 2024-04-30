package ast.FuncionesPrimitivas;

import ast.NodoAST;
import ast.LocatedNode;
import ast.Expresiones.E;
import ast.Metaoperadores.Ambito;
import ast.Vinculacion.Vinculacion;
import ast.TiposDeNodos;

public class Elsif extends LocatedNode implements NodoAST {
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

    public TiposDeNodos nodeKind() {
        return TiposDeNodos.ELSIF;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ELSIF(" + cond.toString() + ")" + ambito.toString());
        if (siguienteElsif != null) 
            sb.append(siguienteElsif.toString());
        if (siguienteElse != null)
            sb.append(siguienteElse.toString());

        return sb.toString();
    }

    @Override
    public void bind(Vinculacion vinc) {
        vinc.abreBloque();
        ambito.bind(vinc);
        vinc.cierraBloque();

        //Elsif
        if (siguienteElsif != null) siguienteElsif.bind(vinc);
        
        //Else
        if (siguienteElse != null) siguienteElse.bind(vinc);

    }
}
