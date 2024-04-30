package ast.Metaoperadores;

import ast.Expresiones.E;
import ast.Vinculacion.Vinculacion;
import ast.Instruccion;
import ast.TiposDeNodos;

public class Asignacion implements Instruccion  {
    private CabecerAsig exp1;
    private E exp2;

    public Asignacion(CabecerAsig exp1, E exp2) {
        this.exp1 = exp1;
        this.exp2 = exp2;
    }
    public TiposDeNodos nodeKind() {
        return TiposDeNodos.ASIGNACION;
    }

    public String toString() {
        return exp1.toString() + " := " + exp2.toString();
    }
    @Override
    public void bind(Vinculacion vinc) {
        exp2.bind(vinc); //Vinculamos la segunda expresión primero para que no se use el elemento declarado en su asignación
        exp1.bind(vinc);
    }
}
