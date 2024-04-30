package ast.FuncionesPrimitivas;

import ast.Expresiones.E;
import ast.Metaoperadores.Ambito;
import ast.Vinculacion.Vinculacion;
import ast.LocatedNode;
import ast.TiposDeNodos;
import ast.Programa;

public class If extends LocatedNode implements Programa {

    private Elsif siguienteElsif = null;
    private Else siguienteElse  = null;

    //ambos o uno deben de ser nulos

    private Ambito ambito;
    private E cond;

    private Programa programa;

    public If(E cond, Ambito Amb, Programa p,Elsif siguienteElsif, Else siguienteElse, int fila, int columna) {
        super(fila, columna);
        this.cond = cond;
        this.ambito = Amb;
        this.siguienteElsif = siguienteElsif;
        this.siguienteElse = siguienteElse;
        this.programa = p;
    }

    public TiposDeNodos nodeKind() {
        return TiposDeNodos.IF;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("IF(" + cond.toString() + ")" + ambito.toString());
        if (siguienteElsif != null) 
            sb.append(siguienteElsif.toString());
        if (siguienteElse != null)
            sb.append(siguienteElse.toString());

        sb.append(programa.toString());
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

        programa.bind(vinc);

    }
}
